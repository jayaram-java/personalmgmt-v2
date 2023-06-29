package com.company.Personalmgmt.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.WebSiteLinkDetailsDto;
import com.company.Personalmgmt.model.WebSiteLinkDetails;
import com.company.Personalmgmt.repository.WebSiteLinkDetailsRepository;
import com.company.Personalmgmt.service.WebSiteLinkService;

@Service
public class WebSiteLinkServiceImpl implements WebSiteLinkService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(WebSiteLinkServiceImpl.class);

	 private static int n = 1;
	 
	@Autowired
	WebSiteLinkDetailsRepository webSiteLinkDetailsRepository;
	
	public boolean saveWebLinkDetails(WebSiteLinkDetails webSiteLinkDetails) {

		webSiteLinkDetailsRepository.save(webSiteLinkDetails);

		return false;
	}

	
	public WebSiteLinkDetails retrieveLastData() {

		Optional<WebSiteLinkDetails> webSiteLinkDetails = webSiteLinkDetailsRepository.findByOrderByIdDesc();

		WebSiteLinkDetails response = webSiteLinkDetails.get();

		return response;
	}


	@Override
	public List<WebSiteLinkDetailsDto> getAllWebSiteLinks() {

		List<WebSiteLinkDetailsDto> ob = new ArrayList<WebSiteLinkDetailsDto>();
		LocalDateTime startTime = LocalDateTime.now();
		
		// a sequence of objects that supports various methods which can be pipelined to produce the desired result

		try {

			List<WebSiteLinkDetails> webSiteLinkDetails = webSiteLinkDetailsRepository.findAll();

			webSiteLinkDetails.stream().forEach(webSiteLinkDetailz -> {
				SimpleDateFormat dateformat = new SimpleDateFormat("dd-MMMM-yyyy");
				WebSiteLinkDetailsDto webSiteLinkDetailsDto = new WebSiteLinkDetailsDto();

				BeanUtils.copyProperties(webSiteLinkDetailz, webSiteLinkDetailsDto);

				webSiteLinkDetailsDto.setCreatedDate(dateformat.format(webSiteLinkDetailz.getCreatedDate()));

				webSiteLinkDetailsDto.setSerialId(n);

				ob.add(webSiteLinkDetailsDto);

				n++;
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			LocalDateTime endTime = LocalDateTime.now();
			Duration latency = Duration.between(startTime,endTime);
			log.info("API | *getAllWebSiteLinks | latency = "+latency);
		}

		return ob;
	}

}
