package com.company.Personalmgmt.serviceimpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.model.TimeSheetDetails;
import com.company.Personalmgmt.repository.TimeSheetDetailsRepository;
import com.company.Personalmgmt.service.TimeSheetService;

@Service
public class TimeSheetServiceImpl implements TimeSheetService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(TimeSheetServiceImpl.class);

	@Autowired
	TimeSheetDetailsRepository timeSheetDetailsRepository;

	@Override
	public List<TimeSheetDetails> getAllTimeSheetDetails() {

		List<TimeSheetDetails> timeSheetDetails = timeSheetDetailsRepository.findAll();

		log.info("API : getAllTimeSheetDetails --> " + timeSheetDetails.toString());

		return timeSheetDetails;
	}

	@Override
	public boolean updateTimeSheetDetails(TimeSheetDetails timeSheetDetails) {
		
		log.info("API name = *updateTimeSheetDetails | input =" + timeSheetDetails.toString());

		//TimeSheetDetails timeSheetDetail = timeSheetDetailsRepository.getById(timeSheetDetails.getId());
		
		Optional<TimeSheetDetails> timeSheetDetail = timeSheetDetailsRepository.findById(timeSheetDetails.getId());

		try {
			timeSheetDetail.get().setTask(timeSheetDetails.getTask());
			timeSheetDetail.get().setModifiedDate(new Date());

			timeSheetDetailsRepository.save(timeSheetDetail.get());
			return true;
		} catch (Exception e) {
			log.error("Exception " + e);
			return false;
		}

	}

	@Override
	public TimeSheetDetails getTimeSheetDetailsFromId(Long id) {
		
		log.info("API name = *getTimeSheetDetailsFromId | input =" + id);


		Optional<TimeSheetDetails> timeSheetDetail = timeSheetDetailsRepository.findById(id);

		return timeSheetDetail.get();
	}

}
