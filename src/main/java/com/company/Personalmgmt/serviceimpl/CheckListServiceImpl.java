package com.company.Personalmgmt.serviceimpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.Personalmgmt.dto.CheckListCategoryDto;
import com.company.Personalmgmt.dto.CheckListDetailDto;
import com.company.Personalmgmt.model.CheckListCategory;
import com.company.Personalmgmt.model.CheckListDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.CheckListCategoryRepository;
import com.company.Personalmgmt.repository.CheckListDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.CheckListService;

@Service
public class CheckListServiceImpl implements CheckListService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(CheckListServiceImpl.class);
	
	@Autowired
	CheckListCategoryRepository checkListCategoryRepository;
	
	@Autowired
	CheckListDetailsRepository checkListDetailsRepository;
	
	@Autowired
	UserRepository userRepository;
		
	@Autowired
	HttpSession httpsession;

	public List<CheckListCategoryDto> getAllCheckListCategory() {

		log.info("API name = *getAllCheckListCategory");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		List<CheckListCategoryDto> checkListCategoryDtos = new ArrayList<CheckListCategoryDto>();

		try {

			List<CheckListCategory> checkListCategories = checkListCategoryRepository.findByUserId(user.get().getId());
			
			log.info("To fetch data through stream");
			
			checkListCategories.stream().forEach(checkListCategory -> {
				
				CheckListCategoryDto checkListCategoryDto = new CheckListCategoryDto();

				BeanUtils.copyProperties(checkListCategory, checkListCategoryDto);

				checkListCategoryDtos.add(checkListCategoryDto);
				
			});

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return checkListCategoryDtos;
	}

	public boolean saveCheckListDetails(CheckListDetailDto checkListDetailDto) {

		log.info("API name = *saveCheckListDetails");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		Date date = new Date();

		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(df.format(date));

			if (checkListDetailDto.getId() == null) {

				CheckListDetails checkListDetails = new CheckListDetails();

				CheckListCategory checkListCategory = checkListCategoryRepository
						.findByName(checkListDetailDto.getParent());

				checkListDetails.setName(checkListDetailDto.getName());
				checkListDetails.setCheckListCategory(checkListCategory);
				checkListDetails.setCreatedBy((String) httpsession.getAttribute("username"));
				checkListDetails.setCreatedDate(date1);
				checkListDetails.setUser(user.get());

				checkListDetailsRepository.save(checkListDetails);

			} else {

				CheckListDetails checkListDetails = checkListDetailsRepository
						.findById((long) checkListDetailDto.getId());

				CheckListCategory checkListCategory = checkListCategoryRepository
						.findByName(checkListDetailDto.getParent());

				checkListDetails.setName(checkListDetailDto.getName());
				checkListDetails.setCheckListCategory(checkListCategory);
				checkListDetails.setModifiedBy((String) httpsession.getAttribute("username"));
				checkListDetails.setModifiedDate(date1);
				checkListDetails.setUser(user.get());

				checkListDetailsRepository.save(checkListDetails);

			}

		} catch (ParseException e) {
			log.info("ParseException " + e);
			e.printStackTrace();
		}

		return true;
	}

	@Transactional(readOnly = true)
	public CheckListDetailDto getCheckListDetailsFromId(Long id) {

		log.info("API name = *getCheckListDetailsFromId");

		CheckListDetailDto checkListDetailDto = new CheckListDetailDto();

		try {

			CheckListDetails checkListDetails = checkListDetailsRepository.findById((long) id);

			checkListDetailDto.setName(checkListDetails.getName());
			checkListDetailDto.setParent(checkListDetails.getCheckListCategory().getName());

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return checkListDetailDto;
	}

	public List<CheckListDetailDto> getAllCheckListDetails() {

		log.info("API name = *getAllCheckListDetails");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		List<CheckListDetailDto> checkListDetailDtos = new ArrayList<CheckListDetailDto>();
		
		 AtomicInteger counter = new AtomicInteger(1);

		try {

			List<CheckListDetails> checkListDetails = checkListDetailsRepository.findByCheckListCategoryIdAndUserId(
					(long) httpsession.getAttribute("checkListCategoryId"), user.get().getId());
			
			log.info("To fetch data through stream");

			checkListDetails.stream().forEach(checkListDetail -> {

				CheckListDetailDto checkListDetailDto = new CheckListDetailDto();

				checkListDetailDto.setSerialId(counter.getAndIncrement());
				checkListDetailDto.setId(checkListDetail.getId());
				checkListDetailDto.setName(checkListDetail.getName());
				checkListDetailDto.setParent(checkListDetail.getCheckListCategory().getName());

				DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy");

				checkListDetailDto.setCreatedDate(df.format(checkListDetail.getCreatedDate()));

				checkListDetailDtos.add(checkListDetailDto);

			});

		

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return checkListDetailDtos;
	}
	
	@Override
	public List<CheckListDetailDto> getRelevantChecklistDetails(String name) {
		log.info("API name = *getAllCheckListDetails");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);

		List<CheckListDetailDto> checkListDetailDtos = new ArrayList<CheckListDetailDto>();
		
		 AtomicInteger counter = new AtomicInteger(1);

		try {

			List<CheckListDetails> checkListDetails = checkListDetailsRepository.findByCheckListCategoryNameAndUserId(
					name, user.get().getId());
			
			log.info("To fetch data through stream");

			checkListDetails.stream().forEach(checkListDetail -> {

				CheckListDetailDto checkListDetailDto = new CheckListDetailDto();

				checkListDetailDto.setSerialId(counter.getAndIncrement());
				checkListDetailDto.setId(checkListDetail.getId());
				checkListDetailDto.setName(checkListDetail.getName());
				checkListDetailDto.setParent(checkListDetail.getCheckListCategory().getName());

				DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy");

				checkListDetailDto.setCreatedDate(df.format(checkListDetail.getCreatedDate()));

				checkListDetailDtos.add(checkListDetailDto);

			});

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return checkListDetailDtos;

	}

	

}
