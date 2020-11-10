package com.miro.widgetcrud.services.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miro.widgetcrud.model.Widget;


class WidgetMemoryServiceTest {

	private WidgetMemoryService widgetService;
	
	private final Long widgetId = 1L;
	
	@BeforeEach
	void setUp() {
		widgetService = new WidgetMemoryService();
		widgetService.save(Widget.builder()
        			.id(widgetId)
        			.positionX(4)
        			.positionY(5)
        			.width(100)
        			.height(200)
        			.zIndex(2)
        			.build());
		
	}
	
	@Test
    void findAll() {

        Set<Widget> widgetSet = widgetService.findAll();

        assertEquals(1, widgetSet.size());
    }
	
	@Test
    void findByIdExistingId() {

        Widget widget = widgetService.findById(widgetId);

        assertEquals(widgetId, widget.getId());
    }

    @Test
    void findByIdNotExistingId() {

        Widget widget = widgetService.findById(5L);

        assertNull(widget);
    }

    @Test
    void findByIdNullId() {

        Widget widget = widgetService.findById(null);

        assertNull(widget);
    }

    @Test
    void saveExistingId() {

        Long id = 2L;

        Widget widget2 = Widget.builder().id(id).build();

        Widget savedWidget = widgetService.save(widget2);

        assertEquals(id, savedWidget.getId());
    }

    @Test
    void saveDuplicateId() {

        Long id = 1L;

        Widget widget2 = Widget.builder().id(id).build();

        Widget savedWidget = widgetService.save(widget2);

        assertEquals(id, savedWidget.getId());
        assertEquals(1, widgetService.findAll().size());
    }

    @Test
    void saveNoId() {

        Widget savedWidget = widgetService.save(Widget.builder().build());
        assertNotNull(savedWidget);
        assertNotNull(savedWidget.getId());
        assertEquals(2, widgetService.findAll().size());
    }

    @Test
    void deleteWidget() {

        widgetService.delete(widgetService.findById(widgetId));

        assertEquals(0, widgetService.findAll().size());

    }

    @Test
    void deleteWithWrongId() {

        Widget widget = Widget.builder().id(5L).build();

        widgetService.delete(widget);

        assertEquals(1, widgetService.findAll().size());
    }

    @Test
    void deleteWithNullId() {

        Widget widget = Widget.builder().build();

        widgetService.delete(widget);

        assertEquals(1, widgetService.findAll().size());
    }

    @Test
    void deleteNull() {

        widgetService.delete(null);

        assertEquals(1, widgetService.findAll().size());

    }

    @Test
    void deleteByIdCorrectId() {

        widgetService.deleteById(widgetId);

        assertEquals(0, widgetService.findAll().size());
    }

    @Test
    void deleteByIdWrongId() {

        widgetService.deleteById(5L);

        assertEquals(1, widgetService.findAll().size());
    }

    @Test
    void deleteByIdNullId() {

        widgetService.deleteById(null);

        assertEquals(1, widgetService.findAll().size());
    }
    
    @Test
    void saveDuplicateZIndex() throws JsonProcessingException {

        Long id = 2L;
        Integer zIndex = 2;
        
        Widget widget2 = Widget.builder().id(id).zIndex(zIndex).build();
        Widget savedWidget = widgetService.save(widget2);
        assertEquals(zIndex, savedWidget.getZIndex());
        
        Widget existingWidget = widgetService.findById(widgetId);
        assertEquals(zIndex+1, existingWidget.getZIndex());
        
    }
    
    @Test
    void findByZIndex() throws JsonProcessingException {
        Integer zIndex = 3;
        
        Widget foundWidget = widgetService.findByZIndex(zIndex);
        assertNotNull(foundWidget);        
    }

}
