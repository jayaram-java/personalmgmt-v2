package com.company.Personalmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.PropertyDto;
import com.company.Personalmgmt.model.PropertyCategory;
import com.company.Personalmgmt.model.PropertyDetail;
import com.company.Personalmgmt.repository.PropertyCategoryRepository;
import com.company.Personalmgmt.repository.PropertyDetailRepository;
import com.company.Personalmgmt.service.PropertyService;

@Service
public class PropertyServiceImpl implements PropertyService {

	@Autowired
	PropertyDetailRepository propertyDetailRepository;

	@Autowired
	PropertyCategoryRepository propertyCategoryRepository;

	@Autowired
	HttpSession httpsession;

//	private Logger logger = //logger.getLogger(ExpenseServiceImpl.class);

	
	public List<PropertyDto> fetchAllPropertyDetails() {

		List<PropertyDto> propertyDtos = new ArrayList<PropertyDto>();

		try {

			List<PropertyDetail> propertyDetails = propertyDetailRepository.findAll();

			int n = 1;

			for (PropertyDetail propertyDetail : propertyDetails) {

				PropertyDto propertyDto = new PropertyDto();

				propertyDto.setSerialId(n);

				BeanUtils.copyProperties(propertyDetail, propertyDto);

				propertyDto.setPropertyCategory(propertyDetail.getPropertyCategory().getName());

				propertyDtos.add(propertyDto);

				n++;
			}

			//logger.info("This is the information");

		} catch (Exception e) {

			//logger.error("This is an error " + e);

		}

		return propertyDtos;
	}

	
	public boolean savePropertyDetails(PropertyDto propertyDto) {

		if (propertyDto.getId() == null) {

			PropertyDetail propertyDetail = new PropertyDetail();
			
			try {

				PropertyCategory propertyCategory = propertyCategoryRepository.findByName(propertyDto.getPropertyCategory());

				propertyDetail.setCount(propertyDto.getCount());

				propertyDetail.setCreatedBy((String) httpsession.getAttribute("username"));

				propertyDetail.setCreatedDate(new Date());

				propertyDetail.setName(propertyDto.getName());
				
				propertyDetail.setDescription(propertyDto.getDescription());

				propertyDetail.setPropertyCategory(propertyCategory);

				propertyDetailRepository.save(propertyDetail);
				
				//logger.info("create part");

			} catch (Exception e) {
				
				//logger.error("This is an error " + e);

			}

		} else {

			try {

				PropertyDetail propertyDetail = propertyDetailRepository.findById((long)propertyDto.getId());

				PropertyCategory propertyCategory = propertyCategoryRepository
						.findByName(propertyDto.getPropertyCategory());

				propertyDetail.setCount(propertyDto.getCount());

				propertyDetail.setName(propertyDto.getName());
				
				propertyDetail.setDescription(propertyDto.getDescription());

				propertyDetail.setPropertyCategory(propertyCategory);
				
				propertyDetail.setModifiedDate(new Date());

				propertyDetailRepository.save(propertyDetail);

				//logger.info("update part");

			} catch (Exception e) {
				//logger.error("This is an error " + e);
			}

		}

		return true;
	}

	
	public PropertyDto getPropertyDetailsFromId(Long id) {

		PropertyDto propertyDto = new PropertyDto();
		
		try {

			PropertyDetail propertyDetail = propertyDetailRepository.findById((long)id);

			propertyDto.setCount(propertyDetail.getCount());

			propertyDto.setName(propertyDetail.getName());

			propertyDto.setPropertyCategory(propertyDetail.getPropertyCategory().getName());
			
			propertyDto.setDescription(propertyDetail.getDescription());

		} catch (Exception e) {

			//logger.error("This is an error " + e);

		}

		return propertyDto;
	}

}
