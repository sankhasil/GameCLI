/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

import com.cli.game.constant.enums.Gender;

/**
 * @author Sankha
 *
 */
public class Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 478973326418188802L;
	private String name;
	private Gender gender;
	private int health, attackDamage;
//	public Character() {
//	}
	public Character(String name, Gender gender, int health, int attackDamage) {
		this.name = name;
		this.gender = gender;
		this.health = health;
		this.attackDamage = attackDamage;
	}
	public String getName() {
		return name;
	}
	public Gender getGender() {
		return gender;
	}
	public int getHealth() {
		return health;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	
	
	
}

