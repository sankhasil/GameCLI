/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

import com.cli.game.constant.enums.SpecialPowerType;

/**
 * @author Sankha
 *
 */
public class SpecialPower implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4126373087303042116L;
	private int amount;
	private SpecialPowerType type;
//	public SpecialPower() {
//		// TODO Auto-generated constructor stub
//	}
	public SpecialPower(int amount, SpecialPowerType type) {
		this.amount = amount;
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public SpecialPowerType getType() {
		return type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SpecialPower other = (SpecialPower) obj;
		if (type != other.type)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "SpecialPower [amount=" + amount + ", type=" + type + "]";
	}
	
	
	
}
