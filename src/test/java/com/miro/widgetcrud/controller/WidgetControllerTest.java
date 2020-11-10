package com.miro.widgetcrud.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;


@ExtendWith(MockitoExtension.class)
class WidgetControllerTest {

	@Mock
	WidgetService widgetService;
	
	@InjectMocks
	WidgetController widgetController;
	
	
	MockMvc mockMvc;
	
	Set<Widget> widgets;
	
	@BeforeEach
    void setUp() {
	    mockMvc = MockMvcBuilders
                .standaloneSetup(widgetController)
                .build();
        widgets = new HashSet<>();
        widgets.add(Widget.builder()
        			.id(1L)
        			.x(4)
        			.y(5)
        			.width(100)
        			.height(200)
        			.zIndex(-3)
        			.build());
        
        widgets.add(Widget.builder()
	    			.id(2L)
	    			.x(4)
	    			.y(5)
	    			.width(100)
	    			.height(200)
	    			.zIndex(1)
	    			.build());
    
        widgets.add(Widget.builder()
    				.id(3L)
	    			.x(4)
	    			.y(5)
	    			.width(100)
	    			.height(200)
	    			.zIndex(2)
	    			.build());
    
    }
	
	
	@Test
	void saveTest() throws Exception {
		String widgetString = "{ \"coordinates\":{  \"x\": 1,  \"y\": 1 }, \"zindex\": 2, \"width\": 1, \"height\": 1}";
		mockMvc.perform(post("/widget")
				.contentType("application/json")
				.content(widgetString))
				.andExpect(status().isOk());
		verify(widgetService).save(any());
	}
	
	@Test
	void updateTest() throws Exception {
		String widgetString = "{ \"coordinates\":{  \"x\": 1,  \"y\": 1 }, \"zindex\": 5, \"width\": 1, \"height\": 1}";
		mockMvc.perform(put("/widget/1")
				.contentType("application/json")
				.content(widgetString))
				.andExpect(status().isOk());
				
		verify(widgetService).save(any());
		
	}
	
	@Test
	void findAll() throws Exception {
		when(widgetService.findAll()).thenReturn(widgets);
		
		mockMvc.perform(get("/widget/all"))
				.andExpect(status().isOk());		
	}

}
