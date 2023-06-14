package fr.solutec.rest;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;

import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.TypeProductRepository;
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
	@Autowired
	private TypeProductRepository typeProductRepo;
	
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
	
	@Test
	@WithMockUser(username = "UserTest", roles = "ADMIN")
	public void creatAlertTest() throws Exception{
		//Alerte fictive
		Alert a = new Alert();
		a.setAlerte("Alerte");
		a.setEmail(false);
		a.setSeuil(2);
		a.setTriggered(false);
		a.setActive(false);
		
		//Test API
		mockMvc.perform(post("/createAlert")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(a)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.alerte").value("Alerte"))
				.andExpect(jsonPath("$.email").value(false))
				.andExpect(jsonPath("$.triggered").value(false))
				.andExpect(jsonPath("$.active").value(false))
				.andExpect(jsonPath("$.seuil").value(2));
	}

	@Test
	@WithMockUser(username = "UserTest", roles = "ADMIN")
	public void deleteAlertTest() throws Exception{
		// Alerte fictive à supprimer
		Alert a = new Alert();
		a.setAlerte("Alerte");
		a.setEmail(false);
		a.setSeuil(2);
		a.setTriggered(false);
		a.setActive(false);
		alertRepo.save(a);

		//Test API
		mockMvc.perform(delete("/deleteAlert/{idAlert}", a.getIdAlert()))
				.andExpect(status().isOk())
				.andExpect(content().string("true"));
	}

	@Test
	@WithMockUser(username = "UserTest", roles = "ADMIN")
	public void deleteTypeProductTest() throws Exception{
		//Type de produit fictif
		TypeProduct t = new TypeProduct();
		t.setNameProduct("TypeTest1");
		typeProductRepo.save(t);

		//Alerte fictive
		Alert a3 = new Alert();
		a3.setAlerte("Alerte");
		a3.setEmail(false);
		a3.setSeuil(2);
		a3.setTriggered(false);
		a3.setActive(false);
		a3.getProducts().add(t);
		alertRepo.save(a3);

		//Appel de l'API
		mockMvc.perform(patch("/deleteTypeProduct/"+ t.getNameProduct() +"/" + a3.getIdAlert()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.alerte").value("Alerte"))
				.andExpect(jsonPath("$.seuil").value(2))
				.andExpect(jsonPath("$.email").value(false))
				.andExpect(jsonPath("$.active").value(false))
				.andExpect(jsonPath("$.triggered").value(false))
				.andExpect(jsonPath("$.products").isEmpty());
	}

	@Test
	@WithMockUser(username = "UserTest", roles = "ADMIN")
	public void deleteTypeProductTest2() throws Exception{
		//Types de produits fictifs
		TypeProduct t2 = new TypeProduct();
		t2.setNameProduct("TypeTest2");
		typeProductRepo.save(t2);

		TypeProduct t3 = new TypeProduct();
		t3.setNameProduct("TypeTest3");
		typeProductRepo.save(t3);

		//Alerte fictive avec deux types de produit
		Alert a4 = new Alert();
		a4.setAlerte("Alerte");
		a4.setEmail(false);
		a4.setSeuil(2);
		a4.setTriggered(false);
		a4.setActive(false);
		a4.getProducts().add(t2);
		a4.getProducts().add(t3);
		alertRepo.save(a4);

		//Appel de l'API
		mockMvc.perform(patch("/deleteTypeProduct/"+ t2.getNameProduct() +"/" + a4.getIdAlert()))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.alerte").value("Alerte"))
				.andExpect(jsonPath("$.seuil").value(2))
				.andExpect(jsonPath("$.email").value(false))
				.andExpect(jsonPath("$.active").value(false))
				.andExpect(jsonPath("$.triggered").value(false))
				.andExpect(jsonPath("$.products").isNotEmpty());
	}
	
	private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }

}
