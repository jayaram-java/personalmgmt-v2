package com.company.Personalmgmt.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.PersonalInfoDto;
import com.company.Personalmgmt.model.PersonalInfo;
import com.company.Personalmgmt.model.WorkingCompanyDetail;
import com.company.Personalmgmt.repository.PersonalInfoRepository;
import com.company.Personalmgmt.repository.WorkingCompanyDetailRepository;
import com.company.Personalmgmt.service.PersonalInfoService;

@Service
public class PersonalInfoServiceImpl implements PersonalInfoService {

	@Autowired
	PersonalInfoRepository personalInfoRepository;

	@Autowired
	WorkingCompanyDetailRepository workingCompanyDetailRepository;

	@Autowired
	HttpSession httpsession;

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(PersonalInfoServiceImpl.class);


	public boolean savePersonalInfo(PersonalInfoDto personalInfoDto) {

		//logger.info("Save personal info details");

		if (personalInfoDto.getId() == null) {

			PersonalInfo personalInfo = new PersonalInfo();

			BeanUtils.copyProperties(personalInfoDto, personalInfo);

			personalInfo.setCreatedDate(new Date());

			personalInfo.setCreatedBy((String) httpsession.getAttribute("username"));

			personalInfoRepository.save(personalInfo);

		}

		return true;
	}

	public List<PersonalInfoDto> fetchAllPersonalInfo() {

		Date date = new Date();

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		String endDate = df.format(date);

		List<PersonalInfoDto> personalInfoDtos = new ArrayList<PersonalInfoDto>();

		List<PersonalInfo> personalInfos = personalInfoRepository.findAll();

		for (PersonalInfo personalInfo : personalInfos) {

			PersonalInfoDto personalInfoDto = new PersonalInfoDto();

			BeanUtils.copyProperties(personalInfo, personalInfoDto);

			String startdate = personalInfo.getDob();

			try {

				Date d1 = df.parse(startdate);

				Date d2 = df.parse(endDate);

				String age = ageCalculator(d1, d2);

				//logger.info("age = " + age);

				personalInfoDto.setAge(String.valueOf(age));

			} catch (Exception e) {

				//logger.error("exception", e);

			}

			personalInfoDtos.add(personalInfoDto);

		}

		return personalInfoDtos;
	}

	public String ageCalculator(Date d1, Date d2) {

		Instant instant1 = d1.toInstant();

		ZonedDateTime zdt1 = instant1.atZone(ZoneId.systemDefault());

		LocalDate date1 = zdt1.toLocalDate();

		Instant instant2 = d2.toInstant();

		ZonedDateTime zdt2 = instant2.atZone(ZoneId.systemDefault());

		LocalDate date2 = zdt2.toLocalDate();

		Period diff = Period.between(date1, date2);

		int month = diff.getMonths();

		int days = diff.getDays();

		int year = diff.getYears();

		String age = String.valueOf(year).concat(" year ").concat(String.valueOf(month)).concat(" month ")
				.concat(String.valueOf(days)).concat(" days ");

		return age;

	}

	public boolean saveWorkingCompanyDetail(WorkingCompanyDetail workingCompanyDetail) {

		if (workingCompanyDetail.getId() == null) {

			workingCompanyDetail.setCreatedDate(new Date());

			workingCompanyDetail.setCreatedBy((String) httpsession.getAttribute("username"));

			workingCompanyDetailRepository.save(workingCompanyDetail);

		}

		return true;
	}

	public List<WorkingCompanyDetail> fetchAllWorkingCompanyDetail() {

		//logger.info("fetch all working company detail");

		List<WorkingCompanyDetail> workingCompanyDetails = workingCompanyDetailRepository.findAll();

		return workingCompanyDetails;
	}

	public List<PersonalInfoDto> fetchPersonalInfo() {
		
		log.info("API name = *fetchPersonalInfo ");
		
		long n = 1;
		
		LocalDateTime startTime = LocalDateTime.now();

		Date date = new Date();

		DateFormat df = new SimpleDateFormat("dd-MM-yyyy");

		String endDate = df.format(date);

		List<PersonalInfoDto> personalInfoDtos = new ArrayList<PersonalInfoDto>();

		Optional<PersonalInfo> personalInfo = personalInfoRepository.findById((long) 1);

		PersonalInfo personalInfoz = personalInfo.get();
		PersonalInfoDto personalInfoDto = new PersonalInfoDto();
		
		BeanUtils.copyProperties(personalInfoz, personalInfoDto);
		
		personalInfoDto.setName(personalInfoz.getName());
		personalInfoDto.setAadhaarNo(personalInfoz.getAadhaarNo());
		personalInfoDto.setPanNo(personalInfoz.getPanNo());
		
		personalInfoDto.setWebLink("-");
		
		personalInfoDto.setId(n);
		
		personalInfoDto.setDesignation("Learner");

		try {

			String age = ageCalculator(df.parse(personalInfoz.getDob()), df.parse(endDate));

			personalInfoDto.setAge(age);

			personalInfoDtos.add(personalInfoDto);
			n++;
			List<WorkingCompanyDetail> workingCompanyDetails = workingCompanyDetailRepository
					.findByPersonalInfoId(personalInfoz.getId());

			for (WorkingCompanyDetail workingCompanyDetail : workingCompanyDetails) {

				PersonalInfoDto personalInfoDtoy = new PersonalInfoDto();

				personalInfoDtoy.setCompanyName(workingCompanyDetail.getCompanyName());

				personalInfoDtoy.setAadhaarNo(" - ");
				personalInfoDtoy.setPanNo(" - ");
				personalInfoDtoy.setId(n);
				personalInfoDtoy.setWebLink(workingCompanyDetail.getWebLink());
				personalInfoDtoy.setDesignation(workingCompanyDetail.getDesignation());

				String agey = "";

				if (workingCompanyDetail.getDor().equals("Not Yet")) {

					agey = ageCalculator(df.parse(workingCompanyDetail.getDoj()), df.parse(endDate));
				} else {

					agey = ageCalculator(df.parse(workingCompanyDetail.getDoj()),
							df.parse(workingCompanyDetail.getDor()));

				}

				personalInfoDtoy.setAge(agey);
				
				personalInfoDtos.add(personalInfoDtoy);
				n++;
			}

		} catch (Exception e) {
			log.error("Exception " + e);
		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *fetchPersonalInfo | latency = " + latency+ " | output = "+personalInfoDtos.toString());
		}

		return personalInfoDtos;
	}

}
