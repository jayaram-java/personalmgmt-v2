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
import com.company.Personalmgmt.model.DepositDetails;
import com.company.Personalmgmt.model.FixedDepositDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.DepositDetailsRepository;
import com.company.Personalmgmt.repository.FixedDepositRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.InvestmentService;
import com.company.Personalmgmt.utils.CommonUtils;

/**
 * This class is used for
 *
 * @author Jayaram
 */
@Service
public class InvestmentServiceImpl implements InvestmentService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(InvestmentServiceImpl.class);

	@Autowired
	DepositDetailsRepository depositDetailsRepository;

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
		List<DepositDetails> depositDetails = depositDetailsRepository.findByUserIdAndIsActive(userId, "Y");

		Double totalPrincipalAmt = 0.0;
		Double totalMaturityAmt = 0.0;

		LocalDateTime startTime = LocalDateTime.now();

		try {

			for (DepositDetails depositDetail : depositDetails) {

				Map<String, Object> ob = new HashMap<String, Object>();

				ob.put("AccountNumber", depositDetail.getDepositAccNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", depositDetail.getDepositDate());
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmt());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFri());
				ob.put("InterestAmt", depositDetail.getInterestAmt());
				ob.put("MaturityAmount", depositDetail.getMaturityAmt());
				ob.put("MaturityDate", depositDetail.getMaturityDate());

				String maturityDate[] = depositDetail.getMaturityDate().split("-");

				String sort = maturityDate[2].concat(maturityDate[1]).concat(maturityDate[0]);

				ob.put("MaturityDateBased", sort);

				totalPrincipalAmt = totalPrincipalAmt + depositDetail.getPrincipalAmt();
				totalMaturityAmt = totalMaturityAmt + depositDetail.getMaturityAmt();
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
	public Map<String, Object> getDepositDetailsById(String accountNo) {

		log.info("API name = *getDepositDetailsById");

		Map<String, Object> res = new HashMap<String, Object>();
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();

		List<DepositDetails> depositDetails = depositDetailsRepository.findByDepositAccNo(accountNo);

		LocalDateTime startTime = LocalDateTime.now();

		try {

			for (DepositDetails depositDetail : depositDetails) {

				double taxFromInterestAmt = 0.0;

				Map<String, Object> ob = new HashMap<String, Object>();

				ob.put("AccountNumber", depositDetail.getDepositAccNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", depositDetail.getDepositDate());
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmt());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFri());
				ob.put("InterestAmt", depositDetail.getInterestAmt());
				ob.put("MaturityAmount", depositDetail.getMaturityAmt());
				ob.put("MaturityDate", depositDetail.getMaturityDate());
				ob.put("Tax", depositDetail.getTaxAmt());

				if (depositDetail.getTaxAmt() != null) {
					taxFromInterestAmt = (depositDetail.getTaxAmt() / depositDetail.getInterestAmt()) * 100;
				}

				String formattedValue = String.format("%.2f", taxFromInterestAmt);

				ob.put("TaxFromInterestAmt(%)", formattedValue);

				String maturityDate[] = depositDetail.getMaturityDate().split("-");
				String sort = maturityDate[2].concat(maturityDate[1]).concat(maturityDate[0]);

				ob.put("MaturityDateBased", sort);
				response.add(ob);
			}

		} catch (Exception e) {
			log.error("Exception " + e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getDepositDetailsById | latency = " + latency);
		}

		List<Map<String, Object>> finalResponse = sortBasedOnMaturityDate(response);

		res.put("DepositDetails", finalResponse);

		return res;
	}

	@Override
	public Map<String, Object> getDepositDetails(String year, String bankName) {
		log.info("API name = *getDepositDetails");

		Map<String, Object> res = new HashMap<String, Object>();
		List<Map<String, Object>> response = new ArrayList<Map<String, Object>>();
		long id = 1;

		List<DepositDetails> depositDetails = null;

		if (year.equals("9999")) {
			depositDetails = depositDetailsRepository.findByBankNameAndIsActive(bankName, "Y");
		} else {
			depositDetails = depositDetailsRepository.findByYearMasterYearAndBankNameAndIsActive(year, bankName, "Y");
		}

		Double totalPrincipalAmt = 0.0;
		Double totalMaturityAmt = 0.0;

		LocalDateTime startTime = LocalDateTime.now();

		try {

			for (DepositDetails depositDetail : depositDetails) {

				Map<String, Object> ob = new HashMap<String, Object>();

				ob.put("AccountNumber", depositDetail.getDepositAccNo());
				ob.put("BankName", depositDetail.getBankName());
				ob.put("OpenDate", depositDetail.getDepositDate());
				ob.put("PrincipalAmt", depositDetail.getPrincipalAmt());
				ob.put("AccountType", "FD-Term deposit");
				ob.put("ROI(%)", depositDetail.getFri());
				ob.put("InterestAmt", depositDetail.getInterestAmt());
				ob.put("MaturityAmount", depositDetail.getMaturityAmt());
				ob.put("MaturityDate", depositDetail.getMaturityDate());

				String maturityDate[] = depositDetail.getMaturityDate().split("-");

				String sort = maturityDate[2].concat(maturityDate[1]).concat(maturityDate[0]);

				ob.put("MaturityDateBased", sort);

				totalPrincipalAmt = totalPrincipalAmt + depositDetail.getPrincipalAmt();
				totalMaturityAmt = totalMaturityAmt + depositDetail.getMaturityAmt();
				response.add(ob);
			}

		} catch (Exception e) {
			log.error("Exception " + e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getDepositDetails | latency = " + latency);
		}

		Map<String, Object> summary = new HashMap<String, Object>();
		summary.put("TotalPrincipalAmt", totalPrincipalAmt);
		summary.put("TotalMaturityAmt", totalMaturityAmt);

		List<Map<String, Object>> finalResponse = sortBasedOnMaturityDate(response);

		res.put("DepositDetails", finalResponse);
		res.put("Summary", summary);

		return res;
	}

}
