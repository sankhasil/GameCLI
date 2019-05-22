/**
 * 
 */
package com.cli.game.constant.enums;

/**
 * @author Sankha
 *
 */
public enum Gender {

	MALE("Male"),FEMALE("Female"),UNKNOWN("unknown");
	
	private String value;
	
	private Gender(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
