/**
 * 
 */
package com.ones.dto;

import org.springframework.data.domain.Sort.Direction;

/**
 * @author son.truong
 * Apr 13, 2017 11:03:56 PM
 */
public class SortDTO {

	private Direction direction = Direction.ASC;
	private String property;

	public SortDTO() {
		
	}

	public Direction getDirection() {
		return direction;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}
}
