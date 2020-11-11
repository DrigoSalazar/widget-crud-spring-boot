package com.miro.widgetcrud.services.springdatajpa;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.repositories.WidgetRepository;


@ExtendWith(MockitoExtension.class)
class WidgetSDJpaServiceTest {

	@Mock
	WidgetRepository widgetRepository;
	
	@InjectMocks
	WidgetSDJpaService service;
	
	Widget returnWidget;
	
	private final Long widgetId = 1L;
	
	@BeforeEach
    void setUp() {
        returnWidget = Widget.builder()
    			.id(widgetId)
    			.x(4)
    			.y(5)
    			.width(100)
    			.height(200)
    			.zIndex(2)
    			.build();
    }

	@Test
    void findAll() {
		List<Widget> returnWidgetsList = new ArrayList<>();
		returnWidgetsList.add(Widget.builder().id(1l).build());
		returnWidgetsList.add(Widget.builder().id(2l).build());
		Page<Widget> returnWidgetsPage = new PageImpl<>(returnWidgetsList);
		        
        when(widgetRepository.findAll((Pageable)any())).thenReturn(returnWidgetsPage);

        List<Widget> widgets = service.findAllSorted(1, 10);

        assertNotNull(widgets);
        assertEquals(2, widgets.size());
    }

    @Test
    void findById() {
        when(widgetRepository.findById(anyLong())).thenReturn(Optional.of(returnWidget));

        Widget widget = service.findById(1L);

        assertNotNull(widget);
    }

    @Test
    void findByIdNotFound() {
        when(widgetRepository.findById(anyLong())).thenReturn(Optional.empty());

        Widget widget = service.findById(1L);

        assertNull(widget);
    }


    @Test
    void save() {
        Widget widgetToSave = Widget.builder().id(1L).build();

        when(widgetRepository.save(any())).thenReturn(returnWidget);

        Widget savedWidget = service.save(widgetToSave);

        assertNotNull(savedWidget);

        verify(widgetRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(returnWidget);

        //default is 1 times
        verify(widgetRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(widgetRepository).deleteById(anyLong());
    }
	
	@Test
	void testFindByZIndex() {
		List<Widget> widgets = new ArrayList<>();
        widgets.add(returnWidget);
		when(widgetRepository.findByZindex(any())).thenReturn(widgets);
		
		Widget foundWidget = service.findByZindex(2);
        assertNotNull(foundWidget);  
	}

}
