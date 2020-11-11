package com.miro.widgetcrud.controller;

import java.util.Date;
import java.util.List;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.validation.constraints.Max;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.miro.widgetcrud.model.CartesianCoordinates;
import com.miro.widgetcrud.model.Widget;
import com.miro.widgetcrud.services.WidgetService;

import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequestMapping("widget")
@RestController
@RequiredArgsConstructor
@Validated
public class WidgetController {

	private final WidgetService widgetService; 
	
	@GetMapping("/all")
	public List<Widget> findAll(
			@RequestParam(defaultValue = "0") Integer pageNo, 
            @RequestParam(defaultValue = "10") @Max(value=500, message="Max 500") Integer pageSize,
            @RequestParam(required = false) Integer fpX, 
            @RequestParam(required = false) Integer fpY, 
            @RequestParam(required = false) Integer spX, 
            @RequestParam(required = false) Integer spY) {
		if(fpX != null 
				&& fpY != null 
				&& spX != null 
				&& spY != null) {
			return widgetService.findAllSorted(pageNo, 
					pageSize, 
					new CartesianCoordinates(fpX, fpY),
					new CartesianCoordinates(spX, spY));
		}
		return widgetService.findAllSorted(pageNo, pageSize);
		
	}
	
	@PostMapping
	public Widget save(@RequestBody @Valid() Widget widget) {
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
	
	@ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handle(ConstraintViolationException ex) {
		ErrorDetails errorDetails = new ErrorDetails(new Date(), "Validation Failed",
				ex.getConstraintViolations().toString());
				return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
 
}
