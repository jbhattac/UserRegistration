package com.jb.registration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class RegistrationControllerTest {

	@Autowired
	private MockMvc mvc;
	
	@Test
	public void testRegularShipmentResponse_validRegistration()
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString( RegistrationRequest.builder()
				 .firstName("Joydeep")
				 .lastName("Bhattacharjee")
				 .userName("joydeep.bha")
				 .password("password").build());
		 
		mvc.perform(post("/userservice/register")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is2xxSuccessful())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("joydeep.bha".hashCode()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("Joydeep"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Bhattacharjee"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("joydeep.bha"))
		;
	}
	
	@Test
	public void testRegularShipmentResponse_duplicateRegistration()
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString( RegistrationRequest.builder()
				 .firstName("John")
				 .lastName("Smith")
				 .userName("john.smith")
				 .password("password")
				 .build());
		 mvc.perform(post("/userservice/register")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().is2xxSuccessful())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").value("john.smith".hashCode()))
					.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("John"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("Smith"))
					.andExpect(MockMvcResultMatchers.jsonPath("$.userName").value("john.smith"));
		mvc.perform(post("/userservice/register")
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().is4xxClientError())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.USER_ALREADY_EXISTS.toString()))
				.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("A user with the given username already exists"))
		;
	}
	
	@Test
	public void testRegularShipmentResponse_invalidUserData()
			throws Exception {

		ObjectMapper mapper = new ObjectMapper();
		 String json = mapper.writeValueAsString( RegistrationRequest.builder()
				 .firstName("Peter")
				 .lastName("Smith")
				 .userName("")
				 .password("password")
				 .build());
		 mvc.perform(post("/userservice/register")
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().is4xxClientError())
					.andExpect(MockMvcResultMatchers.jsonPath("$.code").value(ErrorCode.INVALID_INPUT.toString()))
					.andExpect(MockMvcResultMatchers.jsonPath("$.description").value("Invalid input data"))
					;
	}
}
