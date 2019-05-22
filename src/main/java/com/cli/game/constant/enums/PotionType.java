package com.cli.game.constant.enums;

public enum PotionType {

	HEALTH("health"),MANNA("manna");
	private String value;
	
	public String getValue() {
		return value;
	}

	private PotionType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
