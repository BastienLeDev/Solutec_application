package fr.solutec.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class typeProductTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private TypeProductRepository typeProductRepo;

    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void addTypeProductTest() throws Exception{
        //Type de produit fictif
        TypeProduct t = new TypeProduct();
        t.setNameProduct("TypeTest");

        //Appel de l'API
        mockMvc.perform(post("/typeProduct/add/{login}","UserTest")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(t)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nameProduct").value("TypeTest"));
    }

    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void deleteTypeProductTest() throws Exception {
        //Type de produit fictif
        TypeProduct t1 = new TypeProduct();
        t1.setNameProduct("TypeTest1");
        typeProductRepo.save(t1);

        //Appel de l'API
        mockMvc.perform(delete("/typeProduct/delete/" + t1.getIdTypeProduct() + "/UserTest"))
                .andExpect(status().isOk());
    }


    private String asJsonString(Object obj) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(obj);
    }
}
