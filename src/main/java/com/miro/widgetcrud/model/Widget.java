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

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class Widget extends BaseEntity{
	
	@Builder
	public Widget(Long id, @NotNull Integer positionX, @NotNull Integer positionY, @NotNull Integer zIndex,
			@NotNull @Positive Integer width, @NotNull @Positive Integer height, Date modified) {
		super(id);
		this.positionX = positionX;
		this.positionY = positionY;
		this.zIndex = zIndex;
		this.width = width;
		this.height = height;
		this.modified = modified;
	}

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
