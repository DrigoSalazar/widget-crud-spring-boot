package com.miro.widgetcrud.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.miro.widgetcrud.model.Widget;

@Service
public interface WidgetService extends CrudService<Widget, Long>{
	
	public List<Widget> findAllSorted();
}
