package com.company.Personalmgmt.serviceimpl;

import java.util.Date;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.Personalmgmt.model.AdminLogin;
import com.company.Personalmgmt.model.CheckListCategory;
import com.company.Personalmgmt.model.KeyNotesCategory;
import com.company.Personalmgmt.model.PresentActivity;
import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.AdminLoginRepository;
import com.company.Personalmgmt.repository.CheckListCategoryRepository;
import com.company.Personalmgmt.repository.KeyNotesCategoryRepository;
import com.company.Personalmgmt.repository.PresentActivityRepository;
import com.company.Personalmgmt.repository.UserRepository;
import com.company.Personalmgmt.service.LoginService;




@Service
public class LoginServiceImpl implements LoginService {
	
	 private static final org.slf4j.Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

	
	@Autowired
	AdminLoginRepository adminLoginRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PresentActivityRepository presentActivityRepository;
	
	@Autowired
	CheckListCategoryRepository checkListCategoryRepository;
	
	@Autowired
	KeyNotesCategoryRepository keyNotesCategoryRepository;

	
	@Autowired
	HttpSession httpsession;

	public boolean loginCheck(String username, String password) {
		
		log.info("login checking = "+username + " "+password);

		AdminLogin adminLogin = adminLoginRepository.findByUsernameAndPassword(username, password);

		if (adminLogin == null) {
			return false;

		} else {

			Long id = new Long(1000);

			httpsession.setAttribute("active", "admin");
			httpsession.setAttribute("username", "Admin");
			httpsession.setAttribute("tasklist", id);
			return true;
		}

	}

	public boolean employeeLoginCheck(String username, String password) {
		
		log.info("login checking = "+username + " "+password);

		try {

			int hashCode = password.hashCode();

			User user = userRepository.findByUsernameAndPassword(username, String.valueOf(hashCode));

			if (user == null) {
				return false;

			} else {

				CheckListCategory checkListCategory = checkListCategoryRepository.findByStatusAndUserIdAndPrimaryFlag("active",
						user.getId(),"Y");
				
				KeyNotesCategory keyNotesCategory = keyNotesCategoryRepository.findByStatusAndUserIdAndPrimaryFlag("active",
						user.getId(),"Y");

				Optional<PresentActivity> presentActivity = presentActivityRepository.findById((long) 1);

				PresentActivity presentActivityz = presentActivity.get();

				String info[] = presentActivityz.getName().split("#");

				String name = user.getEmployeename();

				String uName = user.getUsername();

				httpsession.setAttribute("active", "employee");
				httpsession.setAttribute("username", name);
				httpsession.setAttribute("uName", uName);
				httpsession.setAttribute("tasklist", user.getId());

				httpsession.setAttribute("userId", user.getId());
				
				log.info("login username = "+name);

				if (checkListCategory == null) {

					httpsession.setAttribute("currentCheckListCategory", "Week end");

					httpsession.setAttribute("checkListCategoryId", "12");

				} else {

					httpsession.setAttribute("currentCheckListCategory", checkListCategory.getName());

					httpsession.setAttribute("checkListCategoryId", checkListCategory.getId());
					
					httpsession.setAttribute("keyNotesCategoryId", keyNotesCategory.getId());

				}

				httpsession.setAttribute("info1", info[0]);

				httpsession.setAttribute("info2", info[1]);

				httpsession.setAttribute("info3", info[2]);

				httpsession.setAttribute("info4", info[3]);

				httpsession.setAttribute("info5", info[4]);

				if (user.getRole().getRolename().equals("user")) {

					httpsession.setAttribute("rolename", "user");
					
					log.info("Role is user ");

				} else {

					httpsession.setAttribute("rolename", "developer");
					
					log.info("Role is developer ");

				}

				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean checkPassword(String password) {

		int hashCode = password.hashCode();

		String username = (String) httpsession.getAttribute("uName");

		User user = userRepository.findByUsernameAndPassword(username, String.valueOf(hashCode));

		if (user == null) {
			return false;
		} else {

			return true;

		}

	}

	@Override
	public boolean changePassword(String newPassword, String currentPassword) {

		int hashCode = currentPassword.hashCode();
		String username = (String) httpsession.getAttribute("uName");
		User user = userRepository.findByUsernameAndPassword(username, String.valueOf(hashCode));

		if (user == null) {

			return false;

		} else {

			user.setPassword(String.valueOf(newPassword.hashCode()));
			user.setModifiedDate(new Date());
			user.setModifiedBy("user");
			userRepository.save(user);

			return true;
		}

	}

}
