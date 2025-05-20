/**
 * 
 */
package com.company.Personalmgmt.serviceimpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.DoubleAdder;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.Personalmgmt.dto.DepositAccountDetailsDto;
import com.company.Personalmgmt.model.FixedDepositDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.FixedDepositRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.DepositService;
import com.company.Personalmgmt.utils.CommonUtils;

/**
* This class is used for Deposit
*
* @author Jayaram
*/
@Service
public class DepositServiceImpl implements DepositService{
	
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(DepositServiceImpl.class);
	
	@Autowired
	FixedDepositRepository fixedDepositRepository;

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	HttpSession httpsession;

	
	
	@Override
	public Map<String, Object> getAllDepositDetails() {

		log.info("API name = *getAllDepositDetails");

		Map<String, Object> res = new HashMap<>();
		List<Map<String, Object>> response = new ArrayList<>();
		long userId = (Long) httpsession.getAttribute("userId");
		List<FixedDepositDetails> depositDetails = fixedDepositRepository.findByUserIdAndIsActive(userId, true);

		log.info("DEPOSIT RESPONSE = " + depositDetails);

		DoubleAdder totalPrincipalAmt = new DoubleAdder();
		DoubleAdder totalMaturityAmt = new DoubleAdder();

		LocalDateTime startTime = LocalDateTime.now();

		try {
			response = depositDetails.stream().map(depositDetail -> {
				Map<String, Object> ob = new HashMap<>();
				ob.put("id", depositDetail.getDepositId());
				ob.put("AccountNumber", depositDetail.getDepositAccountNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", CommonUtils.convertDateToString(depositDetail.getDepositDate()));
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmount());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFixedInterestRate());
				ob.put("InterestAmt", depositDetail.getInterestAmount());
				ob.put("MaturityAmount", depositDetail.getMaturityAmount());
				ob.put("MaturityDate", CommonUtils.convertDateToString(depositDetail.getMaturityDate()));

				String maturity = CommonUtils.convertDateToString(depositDetail.getMaturityDate());
				String[] maturityDate = maturity.split("-");
				String sort = maturityDate[2] + maturityDate[1] + maturityDate[0];
				ob.put("MaturityDateBased", sort);
				ob.put("MaturityDateObject", depositDetail.getMaturityDate().toLocalDate()); // Store as LocalDate for proper sorting


				// Add to totals
				totalPrincipalAmt.add(depositDetail.getPrincipalAmount().doubleValue());
				totalMaturityAmt.add(depositDetail.getMaturityAmount().doubleValue());

				return ob;
			}).collect(Collectors.toList());

		} catch (Exception e) {
			log.error("Exception ", e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllDepositDetails | latency = " + latency);
		}

		Map<String, Object> summary = new HashMap<>();
		summary.put("TotalPrincipalAmt", totalPrincipalAmt.doubleValue());
		summary.put("TotalMaturityAmt", totalMaturityAmt.doubleValue());

		List<Map<String, Object>> finalResponse = sortBasedOnMaturityDate(response);

		res.put("DepositDetails", finalResponse);
		res.put("Summary", summary);

		return res;
	}

	public static List<Map<String, Object>> sortBasedOnMaturityDate(List<Map<String, Object>> depositDetails) {
	    depositDetails.sort(Comparator.comparing(entry -> (LocalDate) entry.get("MaturityDateObject")));
	    return depositDetails;
	}

	
	public static List<Map<String, Object>> sortBasedOnMaturityDate01(List<Map<String, Object>> depositDetails) {
		Collections.sort(depositDetails, new Comparator<Map<String, Object>>() {

			@Override
			public int compare(Map<String, Object> element1, Map<String, Object> element2) {

				return String.valueOf(element1.get("MaturityDateBased"))
						.compareTo(String.valueOf(element2.get("MaturityDateBased")));
			}
		});
		return depositDetails;
	}
	

	@Override
	@Transactional
	public boolean saveDepositDetails(DepositAccountDetailsDto dto) {
	    log.info("API name = *saveDepositDetails");

	    try {
	        Long userId = (Long) httpsession.getAttribute("userId");
	        if (userId == null) {
	            log.error("User ID not found in session.");
	            return false;
	        }

	        User user = userRepository.findById(userId)
	                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));

	        FixedDepositDetails depositDetails = dto.getId() == null
	                ? new FixedDepositDetails()
	                : fixedDepositRepository.findById(dto.getId())
	                    .orElseThrow(() -> new EntityNotFoundException("Deposit not found with ID: " + dto.getId()));
	        
	        System.out.println("Part 1 "+depositDetails.toString());

	        populateDepositDetails(depositDetails, dto, user);
	        
	        System.out.println("Part 3 "+depositDetails.toString());

	        fixedDepositRepository.save(depositDetails);
	        return true;

	    } catch (Exception e) {
	        log.error("Error occurred while saving deposit details", e);
	        return false;
	    }
	}
	
	private void populateDepositDetails(FixedDepositDetails depositDetails, DepositAccountDetailsDto dto, User user) {
		
		System.out.println("Dto = "+dto.toString());
		
		 int compoundingPerYear = 4; // Quarterly
		BigDecimal principal = BigDecimal.valueOf(dto.getPrincipalAmount());
		BigDecimal rate = BigDecimal.valueOf(dto.getInterestRate()).divide(BigDecimal.valueOf(100));
		BigDecimal compoundFactor = rate.divide(BigDecimal.valueOf(compoundingPerYear), 10, RoundingMode.HALF_EVEN);
	   

	    LocalDate openingDate = LocalDate.parse(dto.getOpeningDate());
	    LocalDate maturityDate = LocalDate.parse(dto.getMaturityDate());

	    long daysBetween = ChronoUnit.DAYS.between(openingDate, maturityDate);
	    double timeInYears = daysBetween / 365.0;
	    
	    BigDecimal maturityAmount = principal.multiply( BigDecimal.ONE.add(compoundFactor).pow(compoundingPerYear * (int)timeInYears));

	   // double maturityAmount = principalAmount * Math.pow(1 + (annualRate / compoundingPerYear), compoundingPerYear * timeInYears);
	    BigDecimal interest = maturityAmount.subtract(principal) ;

	    depositDetails.setBankName(dto.getBankName());
	    depositDetails.setDepositAccountNo(dto.getAccountNumber());
	    depositDetails.setDepositDate(CommonUtils.convertStringToDate(dto.getOpeningDate()));
	    depositDetails.setMaturityDate(CommonUtils.convertStringToDate(dto.getMaturityDate()));
	    depositDetails.setFixedInterestRate(BigDecimal.valueOf(dto.getInterestRate()));
	    depositDetails.setPrincipalAmount(principal);
	    depositDetails.setMaturityAmount(maturityAmount);
	    depositDetails.setInterestAmount(interest);
	    depositDetails.setRemark(dto.getRemark());
	    depositDetails.setNomineeName(dto.getNomineeName());
	    depositDetails.setIsActive(true);
	    depositDetails.setUser(user);

	    int tenureDays = (int) CommonUtils.calculateTenureInDays(dto.getOpeningDate(), dto.getMaturityDate());
	    depositDetails.setTenureDays(tenureDays);
	    
	    System.out.println("Part 2 "+depositDetails.toString());

	    log.debug("Populated deposit details: {}", depositDetails);
	}



	@Override
	public DepositAccountDetailsDto getDepositDetailsFromId(Long id) {

		DepositAccountDetailsDto depositAccountDetailsDto = new DepositAccountDetailsDto();

		log.info("API name = *getDepositDetailsFromId");
		LocalDateTime startTime = LocalDateTime.now();
		try {

			Optional<FixedDepositDetails> fixedDepositDetails = fixedDepositRepository.findById((long) id);
			BeanUtils.copyProperties(fixedDepositDetails.get(), depositAccountDetailsDto);
			depositAccountDetailsDto.setMaturityDate(CommonUtils.convertDateToString(fixedDepositDetails.get().getMaturityDate()));
			depositAccountDetailsDto.setOpeningDate(CommonUtils.convertDateToString(fixedDepositDetails.get().getDepositDate()));
			
			depositAccountDetailsDto.setAccountNumber(fixedDepositDetails.get().getDepositAccountNo());
			depositAccountDetailsDto.setPrincipalAmount(fixedDepositDetails.get().getPrincipalAmount().doubleValue());
			depositAccountDetailsDto.setInterestRate(fixedDepositDetails.get().getFixedInterestRate().doubleValue());
			depositAccountDetailsDto.setMaturityAmount(fixedDepositDetails.get().getMaturityAmount().doubleValue());
			depositAccountDetailsDto.setInterestAmount(fixedDepositDetails.get().getInterestAmount().doubleValue());
			depositAccountDetailsDto.setTaxAmount(fixedDepositDetails.get().getTaxAmount().doubleValue());
			depositAccountDetailsDto.setTenureInMonths(fixedDepositDetails.get().getTenureDays());

		} catch (Exception e) {
			log.error("Exception " + e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getDepositDetailsFromId | latency = " + latency + " | output = "+ depositAccountDetailsDto.toString());
		}
		return depositAccountDetailsDto;
	}



	@Override
	public Map<String, Object> getAllDepositDetails(String bankName) {

		log.info("API name = *getAllDepositDetails");

		Map<String, Object> res = new HashMap<>();
		List<Map<String, Object>> response = new ArrayList<>();
		long userId = (Long) httpsession.getAttribute("userId");
		List<FixedDepositDetails> depositDetails = fixedDepositRepository.findByUserIdAndIsActiveAndBankName(userId, true,bankName);

		log.info("DEPOSIT RESPONSE = " + depositDetails);

		DoubleAdder totalPrincipalAmt = new DoubleAdder();
		DoubleAdder totalMaturityAmt = new DoubleAdder();

		LocalDateTime startTime = LocalDateTime.now();

		try {
			response = depositDetails.stream().map(depositDetail -> {
				Map<String, Object> ob = new HashMap<>();
				ob.put("id", depositDetail.getDepositId());
				ob.put("AccountNumber", depositDetail.getDepositAccountNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", CommonUtils.convertDateToString(depositDetail.getDepositDate()));
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmount());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFixedInterestRate());
				ob.put("InterestAmt", depositDetail.getInterestAmount());
				ob.put("MaturityAmount", depositDetail.getMaturityAmount());
				ob.put("MaturityDate", CommonUtils.convertDateToString(depositDetail.getMaturityDate()));

				String maturity = CommonUtils.convertDateToString(depositDetail.getMaturityDate());
				String[] maturityDate = maturity.split("-");
				String sort = maturityDate[2] + maturityDate[1] + maturityDate[0];
				ob.put("MaturityDateBased", sort);
				ob.put("MaturityDateObject", depositDetail.getMaturityDate().toLocalDate()); // Store as LocalDate for proper sorting

				// Add to totals
				totalPrincipalAmt.add(depositDetail.getPrincipalAmount().doubleValue());
				totalMaturityAmt.add(depositDetail.getMaturityAmount().doubleValue());

				return ob;
			}).collect(Collectors.toList());

		} catch (Exception e) {
			log.error("Exception ", e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllDepositDetails | latency = " + latency);
		}

		Map<String, Object> summary = new HashMap<>();
		summary.put("TotalPrincipalAmt", totalPrincipalAmt.doubleValue());
		summary.put("TotalMaturityAmt", totalMaturityAmt.doubleValue());

		List<Map<String, Object>> finalResponse = sortBasedOnMaturityDate(response);

		res.put("DepositDetails", finalResponse);
		res.put("Summary", summary);
		return res;
	}



}
