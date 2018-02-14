//package com.example.sample_6_restapi.restapi;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
//import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
////
////@AutoConfigureMockMvc
////@RunWith(SpringRunner.class)
////@SpringBootTest(classes = JacksonAutoConfiguration.class)
//
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@ActiveProfiles("unit")
//@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
//public class CustomerRestControllerTest {
//
//    private MockMvc mvc;
//
//    @InjectMocks
//    private CustomerRestControllerTest CustomerRestController;
//
//    @Before
//    public void before() throws Exception {
//        mvc = MockMvcBuilders.standaloneSetup(CustomerRestController).build();
//    }
//
//    @Test
//    public void testGet__Ok() throws Exception {
//        mvc.perform(get("api/customers/1"))
//           .andExpect(status().isOk());
//    }
//
//
//}
