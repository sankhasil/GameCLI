package com.cli.game.constant.enums;

public enum WeaponType {

	SWORD("sword"),MACE("mace"),BOW("bow"),ARROW("arrow");
	private String value;
	
	public String getValue() {
		return value;
	}

	private WeaponType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
