package com.miro.widgetcrud.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="coordinates")
public class CartesianCoordinates extends BaseEntity{
	
	
	@NotNull
	@Column(name = "x")
	private Integer x;
	
	@NotNull
	@Column(name = "y")
	private Integer y;

	public CartesianCoordinates() {
		
	}
	
	public CartesianCoordinates(Long id, Integer x, Integer y) {
		super(id);
	}
		
	@Builder
	public CartesianCoordinates(@NotNull Integer x, @NotNull Integer y) {
		super();
		this.x = x;
		this.y = y;
	}

}
