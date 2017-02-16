package app.service;

import controller.app.restservice.AppTicketService;
import dao.TicketDao;
import entity.Ticket;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by mi on 2/15/17.
 */
public class AppTicketServiceTest {

    //@InjectMocks
    AppTicketService appTicketService = new AppTicketService();

    private MockMvc mockMvc;

    /*@Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(appTicketService).build();
    }*/

  //  @Test
    public void testAll(){
     //   List<Ticket> tickets;
//
//
//        ResponseEntity<?> rsEt = appTicketService.getTicketByFilmTimeAndSeatId(16,4320);
//        System.out.println(rsEt.getBody());
//        assertEquals(200,rsEt.getStatusCode());
     /*   tickets = (List<Ticket>)rsEt.getBody();
        assertEquals(0,tickets.size());
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/api/app/ticket/get-by-film-time/16/4320"))
                    .andExpect(ResponseEntity.status(14).equals(HttpStatus.ACCEPTED));
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
