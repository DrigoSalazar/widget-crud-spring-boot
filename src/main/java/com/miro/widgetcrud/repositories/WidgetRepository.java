package com.miro.widgetcrud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.miro.widgetcrud.model.Widget;

public interface WidgetRepository  extends CrudRepository<Widget, Long>{

	public List<Widget> findByZIndex(Integer zIndex);
	
	Widget findTopByOrderByZIndexDesc();
}
