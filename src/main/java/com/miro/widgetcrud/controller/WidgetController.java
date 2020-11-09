package com.miro.widgetcrud.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.CrudService;

@RequestMapping("widget")
@RestController
public class WidgetController {

	@Autowired
	private CrudService<Widget, Long> widgetService; 
	
	@GetMapping("/all")
	public Set<Widget> findAll() {
		return widgetService.findAll();
		
	}
	
	@PostMapping
	public Widget save(@RequestBody Widget widget) {
		return widgetService.save(widget);
		
	}
	
	
}
