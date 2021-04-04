package com.abcenterprise.ecommerce;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.abcenterprise.ecommerce.model.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
class IntegrationTests {
	
	@Autowired
    private MockMvc mvc;
	
	public void createUser(String userName, String mail, String password, int expectedStatus) throws Exception {
		UserDto user = new UserDto();
		user.setUsername(userName);
		user.setEmail(mail);
		user.setPassword(password);
		user.setFirstname(userName);
		user.setLastname(userName);
		
		checkUser(user, userName, mail, password, false);
		
		ObjectMapper om = new ObjectMapper();
		String userJson =  om.writeValueAsString(user);
		
		MvcResult result = mvc.perform(post("/api/v1/users/register")
				.content(userJson)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is(expectedStatus))
				.andExpect(content()
				.contentTypeCompatibleWith("application/json")).andReturn();
		
		String jsonOut = result.getResponse().getContentAsString();
		
		if(result.getResponse().getStatus() >= 200 && result.getResponse().getStatus() < 300) {
			UserDto userOut = om.readValue(jsonOut, UserDto.class);
			checkUser(userOut, userName, mail, password, true);
		}
	}
	
	public void checkUser(UserDto user, String userName, String mail, String password, boolean encryptedPassword) {
		assertThat(user.getUsername()).isEqualTo(userName);
		assertThat(user.getEmail()).isEqualTo(mail);
		if(encryptedPassword)
			assertThat(user.getPassword()).isNotEqualTo(password);
		else
			assertThat(user.getPassword()).isEqualTo(password);
	}
	
	@Test
	public void createUserOk() throws Exception {
		createUser("test", "test@test.com", "test", 201);
	}
	
	@Test
	public void createUserKoNoUserName() throws Exception {
		createUser(null, "test@test.com", "test", 400);
	}
	
	@Test
	public void createUserKoNomail() throws Exception {
		createUser("test", null, "test", 400);
	}
	
	@Test
	public void createUserKoNoPassword() throws Exception {
		createUser("test", "test@test.com", null, 400);
	}
}
