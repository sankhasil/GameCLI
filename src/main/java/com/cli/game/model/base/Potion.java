/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

import com.cli.game.constant.enums.PotionType;

/**
 * @author Sankha
 *
 */
public class Potion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3622475615838514514L;
	private int potionAmount;
	private PotionType type;

//	public Potion() {
//		// TODO Auto-generated constructor stub
//	}
	public Potion(int potionAmount, PotionType type) {
		this.potionAmount = potionAmount;
		this.type = type;
	}

	public int getPotionAmount() {
		return potionAmount;
	}

	public PotionType getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Potion [amount=" + potionAmount + ", type=" + type + "]";
	}

}
