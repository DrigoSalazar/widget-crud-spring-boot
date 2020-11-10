package com.miro.widgetcrud.model;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartesianCoordinates {
	
	public CartesianCoordinates() {
		
	}
	
	@Builder
	public CartesianCoordinates(@NotNull Integer x, @NotNull Integer y) {
		this.x = x;
		this.y = y;
	}

	@NotNull
	private Integer x;
	
	@NotNull
	private Integer y;
}
