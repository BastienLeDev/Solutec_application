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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @Autowired
    private ProductRepository productRepo;
    
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
    
    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void patchProductTest() throws Exception{
    	//Créer un type de produit fictif 
        TypeProduct t2 = new TypeProduct(null,"type2");
        typeproductRepo.save(t2);
        
        //Créer un produit fictif avant modification
        Product product3 = new Product();
        product3.setTypeProduct(t2);
        product3.setRefProduct("ref1");
        product3.setOwner("owner");
        product3.setReservation(false);
        productRepo.save(product3);
        
        //Créer un produit fictif après modification
        Product product2 = new Product();
        product2.setIdProduct(product3.getIdProduct());
        product2.setTypeProduct(product3.getTypeProduct());
        product2.setRefProduct("ref2");
        product2.setOwner("");
        product2.setReservation(true);
        
        //Appel de l'API à tester
        mockMvc.perform(patch("/patch/product/{login}","UserTest")
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(product2)))
        		.andExpect(status().isOk());
    };
    
    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void deleteProductTest() throws Exception{
    	//Créer un type de produit fictif 
        TypeProduct t3 = new TypeProduct(null,"type3");
        typeproductRepo.save(t3);
        
    	//Créer un produit fictif à supprimé
        Product product4 = new Product();
        product4.setTypeProduct(t3);
        product4.setRefProduct("ref2");
        product4.setOwner("owner");
        product4.setReservation(false);
        productRepo.save(product4);
        
        //Appel de l'API
        mockMvc.perform(delete("/delete/{idProduct}/{login}", product4.getIdProduct(), "UserTest")
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(product4)))
        		.andExpect(status().isOk())
        		.andExpect(content().string("true"));
    }
    
    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void deleteNotProductTest() throws Exception{
    	//Créer un type de produit fictif 
        TypeProduct t3 = new TypeProduct(null,"type3");
        
    	//Créer un produit fictif à supprimé
        Product product4 = new Product();
        product4.setTypeProduct(t3);
        product4.setRefProduct("ref2");
        product4.setOwner("owner");
        product4.setReservation(false);
        
        //Appel de l'API
        mockMvc.perform(delete("/delete/"+ product4.getIdProduct() + "/UserTest")
        		.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(product4)))
        		.andExpect(status().is4xxClientError());
    }
    
    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void getStockTest() throws Exception{
    	//Appel de l'API
    	mockMvc.perform(get("/products/getStock"))
    			.andExpect(status().isOk());
    }
    
    
    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
