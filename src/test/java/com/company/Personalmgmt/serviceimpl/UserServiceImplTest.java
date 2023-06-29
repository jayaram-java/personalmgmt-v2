package com.company.Personalmgmt.serviceimpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.company.Personalmgmt.model.User;
import com.company.Personalmgmt.repository.UserRepository;

public class UserServiceImplTest {

	/*@Mock
	UserRepository userRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	User user;*/

	/*@BeforeEach
	public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);

		user = new User();

		user.setId(1);
		user.setUsername("Ram");
		user.setEmail("jayaramp51096@gmail.com");
		user.setEmployeename("Jayaram");

	}

	@Test
	public void testCheckUsername() {

		when(userRepository.findByUsername(anyString())).thenReturn(user);

		boolean ob = userServiceImpl.checkUsername("Ram");

		// assert

		// assertNotNull(userDTO);
		assertEquals("Ram", user.getUsername());

	}*/

}
