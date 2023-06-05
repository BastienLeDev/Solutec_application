package fr.solutec.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class alertRestTest {
	@Autowired
	private MockMvc mockMvc;
	
	// A revoir car accès refusé avec Spring security + pas d'alerte fictive
	@Test
	public void modifyAlertTest() throws Exception {
		mockMvc.perform(patch("/modifyAlert/1"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$[1].alerte", is("test")))
			.andExpect(jsonPath("$[2].seuil", is(1)))
			.andExpect(jsonPath("$[3].active", is(false)))
			.andExpect(jsonPath("$[4].triggered", is(false)))
			.andExpect(jsonPath("$[5].Email", is(false)));
		
	}

}
