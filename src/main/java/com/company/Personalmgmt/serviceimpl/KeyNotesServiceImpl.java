package com.company.Personalmgmt.serviceimpl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.CheckListCategoryDto;
import com.company.Personalmgmt.dto.KeyNotesDto;
import com.company.Personalmgmt.model.KeyNotesCategory;
import com.company.Personalmgmt.model.KeyNotesDetails;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.KeyNotesCategoryRepository;
import com.company.Personalmgmt.repository.KeyNotesDetailsRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.KeyNotesService;


/**
 * @author Jayaram
 *
 * ${tags} To improve the code standard
 */

@Service
public class KeyNotesServiceImpl implements KeyNotesService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(KeyNotesServiceImpl.class);

	@Autowired
	KeyNotesCategoryRepository keyNotesCategoryRepository;

	@Autowired
	KeyNotesDetailsRepository keyNotesDetailsRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	HttpSession httpsession;

	@Override
	public boolean saveKeyNotesDetails(KeyNotesDto keyNotesDto) {
		log.info("API name = *saveKeyNotesDetails | input =" + keyNotesDto.toString());

		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

		LocalDateTime startTime = LocalDateTime.now();

		try {

			Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(df.format(date));

			if (keyNotesDto.getId() == null) {

				KeyNotesDetails keyNotesDetails = new KeyNotesDetails();
				KeyNotesCategory keyNotesCategory = keyNotesCategoryRepository.findByName(keyNotesDto.getParent());
				BeanUtils.copyProperties(keyNotesDto, keyNotesDetails);

				keyNotesDetails.setCreatedBy((String) httpsession.getAttribute("username"));
				keyNotesDetails.setCreatedDate(date1);
				keyNotesDetails.setKeyNotesCategory(keyNotesCategory);
				keyNotesDetails.setUser(user.get());
				
				keyNotesDetailsRepository.save(keyNotesDetails);

			} else {
				
				KeyNotesDetails keyNotesDetails = keyNotesDetailsRepository.findById((long)keyNotesDto.getId());
				KeyNotesCategory keyNotesCategory = keyNotesCategoryRepository.findByName(keyNotesDto.getParent());
				BeanUtils.copyProperties(keyNotesDto, keyNotesDetails);

				keyNotesDetails.setCreatedBy((String) httpsession.getAttribute("username"));
				keyNotesDetails.setCreatedDate(date1);
				keyNotesDetails.setKeyNotesCategory(keyNotesCategory);
				keyNotesDetails.setUser(user.get());
				
				keyNotesDetailsRepository.save(keyNotesDetails);
				

			}

		} catch (Exception e) {

			log.error("Exception " + e);

		} finally {
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *saveKeyNotesDetails | latency = " + latency);

		}
		return true;
	}

	@Override
	public List<KeyNotesDto> getAllKeyNotesDetails() {
		
		log.info("API name = *getAllKeyNotesDetails");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);
		List<KeyNotesDto> keyNotesDtos = new ArrayList<KeyNotesDto>();
		
		LocalDateTime startTime = LocalDateTime.now();
		
		 AtomicInteger counter = new AtomicInteger(1);
		
		try{
			
			List<KeyNotesDetails> keyNotesDetails = keyNotesDetailsRepository.findByKeyNotesCategoryIdAndUserId((long) httpsession.getAttribute("keyNotesCategoryId"), user.get().getId());
			log.info("stream - pipelined - (source-intermediate-terminal)");
			
			// lamda syntax - parameters -> expressions
			
			keyNotesDetails.stream().forEach(keyNotesDetail ->{
				
				KeyNotesDto keyNotesDto = new KeyNotesDto();
				BeanUtils.copyProperties(keyNotesDetail, keyNotesDto);
				keyNotesDto.setSerialId(counter.getAndIncrement());
				DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy");
				
				keyNotesDto.setCreatedDate(df.format(keyNotesDetail.getCreatedDate()));				
				keyNotesDtos.add(keyNotesDto);
				
			});

		}catch(Exception e){
			log.error("Exception " + e);
		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllKeyNotesDetails | latency = " + latency + " | output = "+keyNotesDtos.toString());
		}
	
		return keyNotesDtos;
	}

	@Override
	public KeyNotesDto getKeyNotesDetailsFromId(Long id) {
		
		KeyNotesDto keyNotesDto = new KeyNotesDto();
		
		log.info("API name = *getKeyNotesDetailsFromId");
		LocalDateTime startTime = LocalDateTime.now();
		try{
			
			KeyNotesDetails keyNotesDetails = keyNotesDetailsRepository.findById((long)id);
			BeanUtils.copyProperties(keyNotesDetails, keyNotesDto);
			keyNotesDto.setParent(keyNotesDetails.getKeyNotesCategory().getName());
		}catch(Exception e){
			log.error("Exception " + e);
		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllKeyNotesDetails | latency = " + latency+ " | output = "+keyNotesDto.toString());
		}
		
		return keyNotesDto;
	}

	@Override
	public List<KeyNotesDto> getRelevantKeyNotesDetails(String name) {
		
		log.info("API name = *getRelevantKeyNotesDetails");

		long userId = (Long) httpsession.getAttribute("userId");

		Optional<User> user = userRepository.findById(userId);
		List<KeyNotesDto> keyNotesDtos = new ArrayList<KeyNotesDto>();
		
		LocalDateTime startTime = LocalDateTime.now();
		
		 AtomicInteger counter = new AtomicInteger(1);
		
		try{
			
			List<KeyNotesDetails> keyNotesDetails = keyNotesDetailsRepository.findByKeyNotesCategoryNameAndUserId(name, user.get().getId());
			log.info("stream - pipelined - (source-intermediate-terminal)");
			
			keyNotesDetails.stream().forEach(keyNotesDetail ->{
				
				KeyNotesDto keyNotesDto = new KeyNotesDto();
				BeanUtils.copyProperties(keyNotesDetail, keyNotesDto);
				keyNotesDto.setSerialId(counter.getAndIncrement());
				DateFormat df = new SimpleDateFormat("dd/MMMM/yyyy");
				
				keyNotesDto.setCreatedDate(df.format(keyNotesDetail.getCreatedDate()));				
				keyNotesDtos.add(keyNotesDto);
				
			});

		}catch(Exception e){
			log.error("Exception " + e);
		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getRelevantKeyNotesDetails | latency = " + latency+ " | output = "+keyNotesDtos.toString());
		}
	
		return keyNotesDtos;

	}

	@Override
	public List<KeyNotesDto> getAllKeyNotesCategory() {
		
		log.info("API name = *getAllKeyNotesCategory");
		long userId = (Long) httpsession.getAttribute("userId");
		Optional<User> user = userRepository.findById(userId);
		LocalDateTime startTime = LocalDateTime.now();

		List<KeyNotesDto> keyNotesDtos = new ArrayList<KeyNotesDto>();
		
		try{
			
			List<KeyNotesCategory> keyNotesCategories = keyNotesCategoryRepository.findByUserId(user.get().getId());
			log.info("stream - pipelined - (source-intermediate-terminal)");
			
			keyNotesCategories.stream().forEach( keyNotesCategory ->{
				
				KeyNotesDto keyNotesDto = new KeyNotesDto();
				keyNotesDto.setName(keyNotesCategory.getName());
				keyNotesDto.setId(keyNotesCategory.getId());
				keyNotesDto.setSequenceOrder(keyNotesCategory.getSequenceOrder());
				
				keyNotesDtos.add(keyNotesDto);
			});
			
			Comparator<KeyNotesDto> com = new Comparator<KeyNotesDto>() {

				@Override
				public int compare(KeyNotesDto o1, KeyNotesDto o2) {

					if (o1.getSequenceOrder() > o2.getSequenceOrder()) {
						return 1;
					} else {
						return -1;
					}
				}

			};
			
			Collections.sort(keyNotesDtos,com);
			
			
		}catch(Exception e){
			log.error("Exception " + e);
		}finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime, endTime);
			log.info("API | *getAllKeyNotesCategory | latency = " + latency+ " | output = "+keyNotesDtos.toString());
		}

		return keyNotesDtos;
	}

}
