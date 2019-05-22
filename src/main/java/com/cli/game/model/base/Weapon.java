/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

import com.cli.game.constant.enums.WeaponType;

/**
 * @author Sankha
 *
 */
public class Weapon implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4346355276405556051L;
	private int hitDamage;
	private WeaponType type;

//	public Weapon() {
//		// TODO Auto-generated constructor stub
//	}

	public Weapon(int hitDamage, WeaponType type) {
		this.hitDamage = hitDamage;
		this.type = type;
	}

	public int getHitDamage() {
		return hitDamage;
	}

	public WeaponType getType() {
		return type;
	}

}
