package com.miro.widgetcrud.services.memory;

import java.util.Set;

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
}
