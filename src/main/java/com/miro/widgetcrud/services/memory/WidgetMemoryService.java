package com.miro.widgetcrud.services.memory;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.miro.widgetcrud.model.CartesianCoordinates;
import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;
import com.miro.widgetcrud.util.WidgetUtil;

@Service
@Profile({"default","memory"})
public class WidgetMemoryService extends AbstractMemoryService<Widget, Long> implements WidgetService{
	
	@Override
	public Set<Widget> findAll() {
        return super.findAll();
    }
	
	public List<Widget> findAllSorted() {
        return super.findAll().stream().sorted(Comparator.comparing(Widget::getZindex)).collect(Collectors.toList());
    }
	
	@Override
	public Widget save(Widget object) {
		object.setModified(new Date());
		if(object.getZindex() == null) {
			object.setZindex(this.getMaxZindex() + 1);
		}
		Widget existingWidget = findByZindex(object.getZindex());
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
    
    public Widget findByZindex(Integer zIndex) {
    	Stream<Widget> stream = map
    		.entrySet()
    		.stream()
    		.filter(obj -> zIndex.equals(obj.getValue().getZindex()))
    		.map(Map.Entry::getValue);
    	return stream.findFirst().orElse(null);
    }
    
    private int getMaxZindex() {
    	Integer max = map.values().stream().mapToInt(obj -> obj.getZindex()).max().orElse(0);
    	return max;
    }
    
    private void moveWidget(Widget widget) {
    	Widget existingWidget = findByZindex(widget.getZindex() + 1);
    	if(existingWidget != null) {
    		moveWidget(existingWidget);
    	}
    	widget.setZindex(widget.getZindex() + 1);
    }

	@Override
	public List<Widget> findAllSorted(Integer pageNo, Integer pageSize) {
		return super.findAll()
				.stream()
				.sorted(Comparator.comparing(Widget::getZindex))
				.skip(pageNo * pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
	}
	
	public List<Widget> findAllSorted(Integer pageNo, Integer pageSize, CartesianCoordinates firstPoint, CartesianCoordinates secondPoint) {
		return super.findAll()
				.stream()
				.filter(obj -> WidgetUtil.isInArea(obj, firstPoint, secondPoint))
				.sorted(Comparator.comparing(Widget::getZindex))
				.skip(pageNo * pageSize)
				.limit(pageSize)
				.collect(Collectors.toList());
	}
	
}
