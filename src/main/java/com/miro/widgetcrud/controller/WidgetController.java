package com.miro.widgetcrud.controller;

import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;

import lombok.RequiredArgsConstructor;

@RequestMapping("widget")
@RestController
@RequiredArgsConstructor
public class WidgetController {

	private final WidgetService widgetService; 
	
	@GetMapping("/all")
	public List<Widget> findAll() {
		return widgetService.findAllSorted();
		
	}
	
	@PostMapping
	public Widget save(@RequestBody @Valid Widget widget) {
		return widgetService.save(widget);		
	}
	
	
	@GetMapping("/{widgetId}")
	public Widget findById(@PathVariable Long widgetId) {
		return widgetService.findById(widgetId);
		
	}
	
	@PutMapping("/{widgetId}")
	public Widget updateById(@PathVariable @Valid Long widgetId, @RequestBody @Valid Widget widget) {
		widget.setId(widgetId);
		return widgetService.save(widget);
	}
	
	@DeleteMapping("/{widgetId}")
	public void deleteById(@PathVariable @Valid Long widgetId) {
		widgetService.deleteById(widgetId);
	}
	
}
