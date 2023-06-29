package com.company.Personalmgmt.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.model.PresentNeighbor;
import com.company.Personalmgmt.repository.PresentNeighborRepository;
import com.company.Personalmgmt.service.NeighbourService;

@Service
public class NeighbourServiceImpl implements NeighbourService {

	@Autowired
	PresentNeighborRepository presentNeighborRepository;

	public List<PresentNeighbor> getAllNeighbourDetails() {

		SimpleDateFormat ob = new SimpleDateFormat("dd-MMMM-yyyy");

		List<PresentNeighbor> presentNeighborz = new ArrayList<PresentNeighbor>();

		List<PresentNeighbor> presentNeighbor = presentNeighborRepository.findAll();

		for (PresentNeighbor presentNeighbory : presentNeighbor) {

			PresentNeighbor presentNeighborq = new PresentNeighbor();

			BeanUtils.copyProperties(presentNeighbory, presentNeighborq);

			presentNeighborq.setCreatedDate2(ob.format(presentNeighbory.getCreatedDate()));

			presentNeighborz.add(presentNeighborq);

		}

		return presentNeighborz;
	}
	
	

}
