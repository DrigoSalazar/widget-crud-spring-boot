package com.miro.widgetcrud.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Widget extends BaseEntity{
	
	@Builder
	public Widget(Long id, @NotNull Integer x, @NotNull Integer y, @NotNull Integer zIndex,
			@NotNull @Positive Integer width, @NotNull @Positive Integer height, Date modified) {
		super(id);
		this.coordinates = new CartesianCoordinates(x, y);
		this.zIndex = zIndex;
		this.width = width;
		this.height = height;
		this.modified = modified;
	}
	
	public Widget(@NotNull Integer x, @NotNull Integer y, @NotNull Integer zIndex,
			@NotNull @Positive Integer width, @NotNull @Positive Integer height, Date modified) {
		this.coordinates = new CartesianCoordinates(x, y);
		this.zIndex = zIndex;
		this.width = width;
		this.height = height;
		this.modified = modified;
	}

	@NotNull
	private CartesianCoordinates coordinates;
	
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
