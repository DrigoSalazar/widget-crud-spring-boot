package com.miro.widgetcrud.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.miro.widgetcrud.model.Widget;

public interface WidgetRepository  extends CrudRepository<Widget, Long>, PagingAndSortingRepository<Widget, Long>{

	List<Widget> findByZindex(Integer zindex);
	
	Widget findTopByOrderByZindexDesc();
	
	List<Widget> findAllByOrderByZindex();
}
