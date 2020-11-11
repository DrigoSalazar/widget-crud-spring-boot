package com.miro.widgetcrud.services.memory;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.miro.widgetcrud.model.CartesianCoordinates;
import com.miro.widgetcrud.model.Widget;


class WidgetMemoryServiceTest {

	private WidgetMemoryService widgetService;
	
	private final Long widgetId = 1L;
	
	@BeforeEach
	void setUp() {
		widgetService = new WidgetMemoryService();
		widgetService.save(Widget.builder()
        			.id(widgetId)
        			.x(4)
        			.y(5)
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
    void updateWidget() {

        Long id = 1L;

        Widget widget2 = Widget.builder()
        		.id(widgetId)
    			.x(5)
    			.y(5)
    			.width(500)
    			.height(500)
    			.zIndex(2)
    			.build();

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
    void saveDuplicateZindex() throws JsonProcessingException {

        Long id = 2L;
        Integer zIndex = 2;
        
        Widget widget2 = Widget.builder().id(id).zIndex(zIndex).build();
        Widget savedWidget = widgetService.save(widget2);
        assertEquals(zIndex, savedWidget.getZindex());
        
        Widget existingWidget = widgetService.findById(widgetId);
        assertEquals(zIndex+1, existingWidget.getZindex());
        
    }
    
    @Test
    void findByZindex() throws JsonProcessingException {
        Integer zIndex = 2;
        
        Widget foundWidget = widgetService.findByZindex(zIndex);
        assertNotNull(foundWidget);        
    }
    
    
    @Test
    void shiftingWidgets1() throws JsonProcessingException {
    	widgetService = new WidgetMemoryService();
    	Integer[] newWidgets ={1,2,3}; 
    	addWidgetsByZindex(newWidgets);
    	
    	Long id = 4L;
        Integer zIndex = 2;
    	
    	Widget widget2 = Widget.builder().id(id).zIndex(zIndex).build();
        Widget savedWidget = widgetService.save(widget2);
        assertEquals(zIndex, savedWidget.getZindex());
        
        Widget foundWidget = widgetService.findById(2L);
        assertEquals(3, foundWidget.getZindex());  
        foundWidget = widgetService.findById(3L);
        assertEquals(4, foundWidget.getZindex());  
        
    }
    
    @Test
    void shiftingWidgets2() throws JsonProcessingException {
    	widgetService = new WidgetMemoryService();
    	Integer[] newWidgets ={1,5,6}; 
    	addWidgetsByZindex(newWidgets);
    	
    	Integer zIndex = 2;
    	
    	Widget widget2 = Widget.builder().zIndex(zIndex).build();
        Widget savedWidget = widgetService.save(widget2);
        assertEquals(zIndex, savedWidget.getZindex());
        
        Widget foundWidget = widgetService.findById(5L);
        assertEquals(5, foundWidget.getZindex());  
        foundWidget = widgetService.findById(6L);
        assertEquals(6, foundWidget.getZindex());  
        
    }
    
    @Test
    void shiftingWidgets3() throws JsonProcessingException {
    	widgetService = new WidgetMemoryService();
    	Integer[] newWidgets ={1,2,4}; 
    	addWidgetsByZindex(newWidgets);
    	
    	Integer zIndex = 2;
    	
    	Widget widget2 = Widget.builder().zIndex(zIndex).build();
        Widget savedWidget = widgetService.save(widget2);
        assertEquals(zIndex, savedWidget.getZindex());
        
        Widget foundWidget = widgetService.findById(2L);
        assertEquals(3, foundWidget.getZindex());  
        foundWidget = widgetService.findById(4L);
        assertEquals(4, foundWidget.getZindex());  
        
    }
    
    void addWidgetsByZindex(Integer[] indexes) {
    	for (Integer integer : indexes) {
    		widgetService.save(Widget.builder()
    			.id(integer.longValue())
    			.zIndex(integer)
    			.build());
		}
    }
    
    @Test
    void findByAreaTest() {
    	widgetService.save(Widget.builder()
    			.id(1L)
    			.width(100)
    			.height(100)
    			.x(50)
    			.y(50)
    			.build());
    	widgetService.save(Widget.builder()
    			.id(2L)
    			.width(100)
    			.height(100)
    			.x(50)
    			.y(100)
    			.build());
    	widgetService.save(Widget.builder()
    			.id(3L)
    			.width(100)
    			.height(100)
    			.x(100)
    			.y(100)
    			.build());
    	CartesianCoordinates firstPoint = new CartesianCoordinates(0, 0);
    	CartesianCoordinates secondPoint = new CartesianCoordinates(100, 150);
    	List<Widget> listResult = widgetService.findAllSorted(0, 10, firstPoint, secondPoint);
    	assertEquals(2, listResult.size());
    }

}
