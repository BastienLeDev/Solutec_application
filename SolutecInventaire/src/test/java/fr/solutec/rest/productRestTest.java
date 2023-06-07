package fr.solutec.rest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class productRestTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    public void addProductTest() throws Exception{
        mockMvc.perform(patch("/add/database/UserTest").content("[{'typeProduct':{'nameProduct':'Type},'refProduct':'0000'}]"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[1].typeProduct.nameProduct", is("Type")))
                .andExpect(jsonPath("$[2].refProduct", is("0000")));
    }
}
