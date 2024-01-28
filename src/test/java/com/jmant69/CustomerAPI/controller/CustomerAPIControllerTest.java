package com.jmant69.CustomerAPI.controller;

import org.hamcrest.Matcher;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmant69.CustomerAPI.entity.Customer;
import com.jmant69.CustomerAPI.exception.CustomerNotFoundException;
import com.jmant69.CustomerAPI.service.CustomerService;

@WebMvcTest(CustomerAPIController.class)
public class CustomerAPIControllerTest {
	
    private static final String END_POINT_PATH = "/customer";
    
    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;
    @MockBean private CustomerService service;
 
    @Test
    public void testAddShouldReturn201NullId() throws Exception {
        Customer newCustomer = new Customer();
        newCustomer.setCustomerRef(1L);
        newCustomer.setCustomerName("Jimmy");
       
        String requestBody = objectMapper.writeValueAsString(newCustomer);
        
        mockMvc.perform(MockMvcRequestBuilders.post(END_POINT_PATH).contentType("application/json")
                .content(requestBody))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/customer/1"))
                .andDo(MockMvcResultHandlers.print())
        ;
    }

   
    @Test
    public void testGetShouldReturn404NotFound() throws Exception {
        Long customerRef = 500L;
        String requestURI = END_POINT_PATH + "/" + customerRef;
     
//        Mockito.when(service.get(customerRef).thenThrow(CustomerNotFoundException.class));
     
        mockMvc.perform(MockMvcRequestBuilders.get(requestURI))
            .andExpect(MockMvcResultMatchers.status().isNotFound())
            .andDo(MockMvcResultHandlers.print());
    }
 
}
