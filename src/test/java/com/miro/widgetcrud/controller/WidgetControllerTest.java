package com.miro.widgetcrud.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;


@ExtendWith(MockitoExtension.class)
class WidgetControllerTest {

	@Mock
	WidgetService widgetService;
	
	@InjectMocks
	WidgetController widgetController;
	
	
	MockMvc mockMvc;
	
	private ObjectMapper objectMapper;
	
	Set<Widget> widgets;
	
	@BeforeEach
    void setUp() {
		objectMapper = new ObjectMapper();
        mockMvc = MockMvcBuilders
                .standaloneSetup(widgetController)
                .build();
        widgets = new HashSet<>();
        widgets.add(Widget.builder()
        			.id(1L)
        			.positionX(4)
        			.positionY(5)
        			.width(100)
        			.height(200)
        			.zIndex(-3)
        			.build());
        
        widgets.add(Widget.builder()
	    			.id(2L)
	    			.positionX(4)
	    			.positionY(5)
	    			.width(100)
	    			.height(200)
	    			.zIndex(1)
	    			.build());
    
        widgets.add(Widget.builder()
    				.id(3L)
	    			.positionX(4)
	    			.positionY(5)
	    			.width(100)
	    			.height(200)
	    			.zIndex(2)
	    			.build());
    
    }
	
	
	@Test
	void saveTest() throws Exception {
		Widget widget = new Widget(1,1,1,1,1,null);
		mockMvc.perform(post("/widget")
				.contentType("application/json")
				.content(objectMapper.writeValueAsString(widget)))
				.andExpect(status().isOk());
	}
	
	@Test
	void findAll() throws Exception {
		when(widgetService.findAll()).thenReturn(widgets);
		
		MvcResult result = mockMvc.perform(get("/widget/all"))
				.andExpect(status().isOk()).andReturn();
		System.out.println(result.getResponse().getContentAsString());
	}

}
