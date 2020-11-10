package com.miro.widgetcrud.services.memory;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;

@Service
public class WidgetMemoryService extends AbstractMemoryService<Widget, Long> implements WidgetService{
	
	@Override
    public Set<Widget> findAll() {
        return super.findAll();
    }
	
	@Override
	public Widget save(Widget object) {
		object.setModified(new Date());
		if(object.getZIndex() == null) {
			object.setZIndex(this.getMaxZIndex() + 1);
		}
		Widget existingWidget = findByZIndex(object.getZIndex());
    	if(existingWidget != null) {
    		moveWidget(existingWidget);
    	}
		return super.save(object);
	}
	
	@Override
    public Widget findById(Long id) {
        return super.findById(id);
    }
	
	@Override
    public void delete(Widget object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
    
    public Widget findByZIndex(Integer zIndex) {
    	Stream<Widget> stream = map
    		.entrySet()
    		.stream()
    		.filter(obj -> zIndex.equals(obj.getValue().getZIndex()))
    		.map(Map.Entry::getValue);
    	return stream.findFirst().orElse(null);
    }
    
    private int getMaxZIndex() {
    	Integer max = map.values().stream().mapToInt(obj -> obj.getZIndex()).max().orElse(0);
    	return max;
    }
    
    private void moveWidget(Widget widget) {
    	Widget existingWidget = findByZIndex(widget.getZIndex() + 1);
    	if(existingWidget != null) {
    		moveWidget(existingWidget);
    	}
    	widget.setZIndex(widget.getZIndex() + 1);
    }
}
