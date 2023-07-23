package com.company.Personalmgmt.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.dto.EmployeeDto;
import com.company.Personalmgmt.model.EmployeeTaskDetail;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.EmployeeTaskDetailRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private static final org.slf4j.Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

	@Autowired
	EmployeeTaskDetailRepository employeeTaskDetailRepository;

	@Autowired
	HttpSession httpsession;

	@Autowired
	UserRepository userRepository;

	public List<EmployeeDto> getAllEmployeeTask() {

		log.info("API name = *getAllEmployeeTask");

		Long employeeid = (Long) httpsession.getAttribute("tasklist");

		List<EmployeeTaskDetail> employeeTaskDetails = employeeTaskDetailRepository
				.findByOfficeOrderByIdDesc("Aspire Systems");

		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();

		Long adminid = new Long(1000);

		int n = 1;

		try {

			if (employeeid.equals(adminid)) {

				for (EmployeeTaskDetail employeeTaskDetail : employeeTaskDetails) {

					EmployeeDto employeeDto = new EmployeeDto();

					BeanUtils.copyProperties(employeeTaskDetail, employeeDto);

					employeeDto.setEmployeeName(employeeTaskDetail.getUser().getEmployeename());

					employeeDto.setSerialId(n);

					employeeDtos.add(employeeDto);
					n++;
				}

			} else {

				for (EmployeeTaskDetail employeeTaskDetail : employeeTaskDetails) {

					if (employeeid.equals(employeeTaskDetail.getUser().getId())) {

						EmployeeDto employeeDto = new EmployeeDto();

						BeanUtils.copyProperties(employeeTaskDetail, employeeDto);

						employeeDto.setSerialId(n);

						employeeDtos.add(employeeDto);

						n++;

					}
				}

			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return employeeDtos;
	}

	public boolean saveEmployeeDetails(EmployeeDto employeeDto) {

		Long employeeid = (Long) httpsession.getAttribute("tasklist");

		log.info("API name = *saveEmployeeDetails " + employeeid);

		try {

			if (employeeDto.getId() == null) {

				EmployeeTaskDetail employeeTaskDetail = new EmployeeTaskDetail();

				employeeTaskDetail.setName(employeeDto.getName());
				employeeTaskDetail.setDescription(employeeDto.getDescription());
				employeeTaskDetail.setStartDate(employeeDto.getStartDate());
				employeeTaskDetail.setEndDate(employeeDto.getEndDate());
				employeeTaskDetail.setStatus(employeeDto.getStatus());
				employeeTaskDetail.setTaskCategory(employeeDto.getTaskCategory());
				employeeTaskDetail.setOffice("Aspire Systems");

				Optional<User> user = userRepository.findById((long) employeeid);

				employeeTaskDetail.setUser(user.get());

				employeeTaskDetailRepository.save(employeeTaskDetail);

			} else {

				EmployeeTaskDetail employeeTaskDetail = employeeTaskDetailRepository
						.findById((long) employeeDto.getId());

				employeeTaskDetail.setName(employeeDto.getName());
				employeeTaskDetail.setDescription(employeeDto.getDescription());
				employeeTaskDetail.setStartDate(employeeDto.getStartDate());
				employeeTaskDetail.setEndDate(employeeDto.getEndDate());
				employeeTaskDetail.setStatus(employeeDto.getStatus());
				employeeTaskDetail.setTaskCategory(employeeDto.getTaskCategory());

				employeeTaskDetailRepository.save(employeeTaskDetail);
			}
		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return true;
	}

	public EmployeeDto getEmployeeTaskFromid(Long id) {

		log.info("API name = *getEmployeeTaskFromid");

		EmployeeDto employeeDto = new EmployeeDto();

		try {

			Optional<EmployeeTaskDetail> employeeTaskDetail = employeeTaskDetailRepository.findById(id);

			employeeDto.setName(employeeTaskDetail.get().getName());
			employeeDto.setDescription(employeeTaskDetail.get().getDescription());
			employeeDto.setStartDate(employeeTaskDetail.get().getStartDate());
			employeeDto.setEndDate(employeeTaskDetail.get().getEndDate());
			employeeDto.setStatus(employeeTaskDetail.get().getStatus());
			employeeDto.setTaskCategory(employeeTaskDetail.get().getTaskCategory());

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return employeeDto;
	}

	public boolean deleteEmployeeTaskFromid(Long id) {

		log.info("API name = *deleteEmployeeTaskFromid");

		try {

			EmployeeTaskDetail employeeTaskDetail = employeeTaskDetailRepository.findById((long) id);

			if (employeeTaskDetail != null) {
				employeeTaskDetailRepository.deleteById(id);
				return true;
			}

		} catch (Exception e) {

			log.info("Exception " + e);

		}

		return false;
	}

	@Override
	public List<EmployeeDto> getTaskDetailsBasedonProject(String project) {

		log.info("API name = *getTaskDetailsBasedonProject");

		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>();

		try {

			List<EmployeeTaskDetail> employeeTaskDetails = employeeTaskDetailRepository
					.findByOfficeOrderByIdDesc(project);
			
			employeeTaskDetails.stream().forEach(employeeTaskDetail ->{
				
				EmployeeDto employeeDto = new EmployeeDto();
				
				BeanUtils.copyProperties(employeeTaskDetail, employeeDto);
				
				employeeDtos.add(employeeDto);
				
			});

			
		} catch (Exception e) {
			e.printStackTrace();
			log.info("Exception " + e);
		}

		return employeeDtos;
	}

}
