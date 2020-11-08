package com.miro.widgetcrud.model;

import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Widget {
	
	private long id;
	
	private int positionX;
	
	private int positionY;
	
	private int zIndex;
	
	private int width;
	
	private int height;
	
	private Date modified;
	
}
