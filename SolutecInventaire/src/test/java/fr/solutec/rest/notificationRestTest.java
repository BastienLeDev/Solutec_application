package fr.solutec.rest;

import fr.solutec.entities.Notification;
import fr.solutec.repository.NotificationRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Date;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class notificationRestTest {
    @Autowired
    private NotificationRepository notificationRepo;

    @Autowired
    private MockMvc mockMvc;

    @Test
    @WithMockUser(username = "UserTest", roles = "ADMIN")
    public void deleteNotificationTest() throws Exception {
        //Notification fictive
        Notification n = new Notification();
        n.setAlerte("AlerteNotif");
        n.setDate(new Date());
        notificationRepo.save(n);

        //Appel de l'API
        mockMvc.perform(delete("/deleteNotification/" + n.getIdNotification()))
                .andExpect(status().isOk());
    }
}
