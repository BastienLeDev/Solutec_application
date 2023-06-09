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
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.solutec.entities.Alert;
import fr.solutec.repository.AlertRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class alertRestTest {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private AlertRepository alertRepo;
	
	@Test
	@WithMockUser(username = "UserTest", roles = "ADMIN")
	public void modifyAlertTest() throws Exception {
		//Alerte fictive avant modification
		Alert a = new Alert();
		a.setAlerte("Alerte");
		a.setIdAlert((long) 1);
		a.setSeuil(0);
		a.setActive(false);
		a.setEmail(false);
		a.setTriggered(false);
		alertRepo.save(a);
		
		//Alerte fictive après modification
		Alert a2 = new Alert();
		a2.setAlerte("Alerte_après_Test");
		a2.setSeuil(10);
		a2.setActive(true);
		a2.setEmail(false);
		a2.setTriggered(false);
		
		mockMvc.perform(patch("/modifyAlert/{idAlert}",a.getIdAlert())
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(a2))
				)
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.alerte").value("Alerte_après_Test"))
			.andExpect(jsonPath("$.seuil").value(10))
			.andExpect(jsonPath("$.active").value(true))
			.andExpect(jsonPath("$.triggered").value(false))
			.andExpect(jsonPath("$.email").value(false));
	}
	
	private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
