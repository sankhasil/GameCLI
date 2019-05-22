package com.cli.game.constant.enums;

public enum SpecialPowerType {

	THUNDER("thunder"),DAMAGE_ALL("damage all"),FIRE("fire"),RAGE_KILL("rage kill"),HEAL("heal");
	private String value;
	
	public String getValue() {
		return value;
	}

	private SpecialPowerType(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return getValue();
	}
}
