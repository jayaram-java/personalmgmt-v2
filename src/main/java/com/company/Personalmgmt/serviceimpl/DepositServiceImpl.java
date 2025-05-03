/**
 * 
 */
package com.company.Personalmgmt.serviceimpl;

import java.math.BigDecimal;
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

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
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

		Map<String, Object> res = new HashMap<String, Object>();
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		long userId = (Long) httpsession.getAttribute("userId");
		List<FixedDepositDetails> depositDetails = fixedDepositRepository.findByUserIdAndIsActive(userId, true);
		
		log.info("DEPOSIT RESPONSE = "+depositDetails.toString());

		Double totalPrincipalAmt = 0.0;
		Double totalMaturityAmt = 0.0;

		LocalDateTime startTime = LocalDateTime.now();
		
		try {

			for (FixedDepositDetails depositDetail : depositDetails) {

				Map<String, Object> ob = new HashMap<String, Object>();

				ob.put("AccountNumber", depositDetail.getDepositAccountNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", CommonUtils.convertDateToString(depositDetail.getDepositDate()));
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmount());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFixedInterestRate());
				ob.put("InterestAmt", depositDetail.getInterestAmount());
				ob.put("MaturityAmount", depositDetail.getMaturityAmount());
				ob.put("MaturityDate", CommonUtils.convertDateToString(depositDetail.getMaturityDate()));
				
			    String maturity =	CommonUtils.convertDateToString(depositDetail.getMaturityDate());

				String maturityDate[] = maturity.split("-");

				String sort = maturityDate[2].concat(maturityDate[1]).concat(maturityDate[0]);

				ob.put("MaturityDateBased", "");

				totalPrincipalAmt = totalPrincipalAmt + depositDetail.getPrincipalAmount().doubleValue();
				totalMaturityAmt = totalMaturityAmt + depositDetail.getMaturityAmount().doubleValue();
				response.add(ob);
			}

		} catch (Exception e) {
			log.error("Exception " + e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllDepositDetails | latency = " + latency);
		}

		Map<String, Object> summary = new HashMap<String, Object>();
		summary.put("TotalPrincipalAmt", totalPrincipalAmt);
		summary.put("TotalMaturityAmt", totalMaturityAmt);

		List<Map<String, Object>> finalResponse = sortBasedOnMaturityDate(response);

		res.put("DepositDetails", finalResponse);
		res.put("Summary", summary);

		return res;
	
	}
	
	public static List<Map<String, Object>> sortBasedOnMaturityDate(List<Map<String, Object>> depositDetails) {
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
	public boolean saveDepositDetails(DepositAccountDetailsDto depositAccountDetailsDto) {

		log.info("API name = *saveDepositDetails");

		try {

			long userId =  (Long) httpsession.getAttribute("userId");

			Optional<User> user = userRepository.findById(userId);

			if (depositAccountDetailsDto.getId() == null) {

				FixedDepositDetails depositDetails = new FixedDepositDetails();

				double principalAmount = depositAccountDetailsDto.getPrincipalAmount();
				double interestRate = depositAccountDetailsDto.getInterestRate();

				depositDetails.setBankName(depositAccountDetailsDto.getBankName());
				depositDetails.setDepositAccountNo(depositAccountDetailsDto.getAccountNumber());
				depositDetails
						.setDepositDate(CommonUtils.convertStringToDate(depositAccountDetailsDto.getOpeningDate()));
				depositDetails.setFixedInterestRate(BigDecimal.valueOf(interestRate));
				
				depositDetails.setRemark(depositAccountDetailsDto.getRemark());
				depositDetails.setNomineeName(depositAccountDetailsDto.getNomineeName());

				depositDetails.setIsActive(true);

				depositDetails
						.setMaturityDate(CommonUtils.convertStringToDate(depositAccountDetailsDto.getMaturityDate()));
				depositDetails.setPrincipalAmount(BigDecimal.valueOf(principalAmount));
				depositDetails.setUser(user.get());

				LocalDate openingDate = LocalDate.parse(depositAccountDetailsDto.getOpeningDate());
				LocalDate maturityDate = LocalDate.parse(depositAccountDetailsDto.getMaturityDate());

				long daysBetween = ChronoUnit.DAYS.between(openingDate, maturityDate);
				double timeInYears = daysBetween / 365.0;

				double annualRate = interestRate / 100; // convert to decimal
				int compoundingPerYear = 4; // quarterly
			     

			//	double maturityAmount = principalAmount * Math.pow((1 + interestRate / 100), timeInYears);
				 double maturityAmount = principalAmount * Math.pow( 1 + (annualRate / compoundingPerYear), compoundingPerYear * timeInYears );
				double interest = maturityAmount - principalAmount;
				
				
				depositDetails.setMaturityAmount(BigDecimal.valueOf(maturityAmount));
				depositDetails.setInterestAmount(BigDecimal.valueOf(interest));
				
				
				System.out.println(depositDetails.toString());

				CommonUtils.convertStringToDate(depositAccountDetailsDto.getOpeningDate());

				long tenureInDays = CommonUtils.calculateTenureInDays(depositAccountDetailsDto.getOpeningDate(),
						depositAccountDetailsDto.getMaturityDate());

				depositDetails.setTenureDays((int) tenureInDays);

				fixedDepositRepository.save(depositDetails);

			} else {

			}

		} catch (Exception e) {
			log.info("ParseException " + e);
			e.printStackTrace();
		}
		return true;
	}


}
