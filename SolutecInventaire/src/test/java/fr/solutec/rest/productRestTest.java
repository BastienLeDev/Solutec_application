package fr.solutec.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import fr.solutec.entities.Product;
import fr.solutec.entities.TypeProduct;
import fr.solutec.repository.ProductRepository;
import fr.solutec.repository.TypeProductRepository;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class productRestTest {
	
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeProductRepository typeproductRepo;
    
    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void addProductTest() throws Exception{
    	//Créer un type de produit fictif 
        TypeProduct t1 = new TypeProduct(null,"type");
        typeproductRepo.save(t1);
        
    	// Créer un objet de test pour le produit
        Product product = new Product();
        product.setTypeProduct(t1);
        product.setRefProduct("ref");
        product.setOwner("owner");
        
        //Appel de l'API à tester :
         mockMvc.perform(post("/add/database/{login}","UserTest")	
 				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(product)))
        		.andExpect(status().isOk());
    }
    
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
