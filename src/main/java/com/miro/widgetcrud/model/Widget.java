package com.miro.widgetcrud.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
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
@Entity
@Table(name = "widget")
public class Widget extends BaseEntity{
	
	@NotNull(message = "Coordinates X and Y are required. coordinates:{x: int, y: int }")
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "coordinates_id", referencedColumnName = "id")
	private CartesianCoordinates coordinates;
	
	@Column(name = "zindex")
	private Integer zindex;
	
	@NotNull(message = "width is required")
	@Positive(message = "width must be a positive integer")
	@Column(name = "width")
	private Integer width;
	
	@NotNull(message = "height is required")
	@Positive(message = "height must be a positive integer")
	@Column(name = "height")
	private Integer height;
	
	@Column(name = "modified")
	private Date modified;
	
	@Builder
	public Widget(Long id, @NotNull Integer x, @NotNull Integer y, @NotNull Integer zIndex,
			@NotNull @Positive Integer width, @NotNull @Positive Integer height, Date modified) {
		super(id);
		this.coordinates = new CartesianCoordinates(x, y);
		this.zindex = zIndex;
		this.width = width;
		this.height = height;
		this.modified = modified;
	}
	
	public Widget(@NotNull Integer x, @NotNull Integer y, @NotNull Integer zIndex,
			@NotNull @Positive Integer width, @NotNull @Positive Integer height, Date modified) {
		this.coordinates = new CartesianCoordinates(x, y);
		this.zindex = zIndex;
		this.width = width;
		this.height = height;
		this.modified = modified;
	}
	
}
