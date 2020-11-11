package com.miro.widgetcrud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.miro.widgetcrud.model.Widget;

public interface WidgetRepository  extends CrudRepository<Widget, Long>{

	List<Widget> findByZindex(Integer zindex);
	
	Widget findTopByOrderByZindexDesc();
	
	List<Widget> findAllByOrderByZindex();
}
