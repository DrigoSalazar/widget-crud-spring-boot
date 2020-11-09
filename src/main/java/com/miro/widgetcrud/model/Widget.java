package com.miro.widgetcrud.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Widget extends BaseEntity{
	
	@NotNull
	private Integer positionX;
	
	@NotNull
	private Integer positionY;
	
	@NotNull
	private Integer zIndex;
	
	@NotNull
	@Positive
	private Integer width;
	
	@NotNull
	@Positive
	private Integer height;
	
	private Date modified;
	
}
