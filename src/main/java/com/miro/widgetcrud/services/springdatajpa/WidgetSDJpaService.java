package com.miro.widgetcrud.services.springdatajpa;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.repositories.WidgetRepository;
import com.miro.widgetcrud.services.WidgetService;

@Service
@Profile("springdatajpa")
public class WidgetSDJpaService implements WidgetService {

	private final WidgetRepository widgetRepository;
	
	public WidgetSDJpaService(WidgetRepository widgetRepository) {
		this.widgetRepository = widgetRepository;
	}
	
	@Override
	public Set<Widget> findAll() {
		Set<Widget> widgets = new HashSet<>();
		widgetRepository.findAll().forEach(widgets::add);;
		return widgets;
	}

	public List<Widget> findAllSorted() {
		List<Widget> widgetList = widgetRepository.findAllByOrderByZindex();
		return widgetList;
	}

	@Override
	public Widget findById(Long id) {
		return widgetRepository.findById(id).orElse(null);
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
		return widgetRepository.save(object);
	}

	@Override
	public void delete(Widget object) {
		widgetRepository.delete(object);

	}

	@Override
	public void deleteById(Long id) {
		widgetRepository.deleteById(id);

	}
	
	public Widget findByZindex(Integer zIndex) {
		List<Widget> widgets = widgetRepository.findByZindex(zIndex);
		return widgets.size()>0 ? widgets.get(0) : null;
	}

	private void moveWidget(Widget widget) {
    	Widget existingWidget = findByZindex(widget.getZindex() + 1);
    	if(existingWidget != null) {
    		moveWidget(existingWidget);
    	}
    	widget.setZindex(widget.getZindex() + 1);
    }
	
	private int getMaxZindex() {
		Widget topZindexWidget = widgetRepository.findTopByOrderByZindexDesc();
    	return topZindexWidget != null ? topZindexWidget.getZindex() : 0;
    }
}
