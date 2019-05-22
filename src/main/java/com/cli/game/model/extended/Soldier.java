/**
 * 
 */
package com.cli.game.model.extended;

import java.io.Serializable;

import com.cli.game.constant.enums.Gender;
import com.cli.game.model.base.Armor;
import com.cli.game.model.base.Character;
import com.cli.game.model.base.Weapon;

/**
 * @author Sankha
 *
 */
public class Soldier extends Character implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3294450506112539140L;
	private Armor armor;
	private Weapon weapon;
//	public Soldier() {
//		super();
//	}
	/**
	 * @param name
	 * @param gender
	 * @param health
	 * @param attackDamage
	 */
	public Soldier(String name, Gender gender, int health, int attackDamage,Armor armor, Weapon weapon) {
		super(name, gender, health, attackDamage);
		this.armor = armor;
		this.weapon = weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public Weapon getWeapon() {
		return weapon;
	}


}
