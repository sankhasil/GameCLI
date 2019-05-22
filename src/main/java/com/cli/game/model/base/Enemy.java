/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

/**
 * @author Sankha
 *
 */
public class Enemy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3731686689853860743L;
	private int attackDamage, health, lootDropChance;

//	public Enemy() {
//		// TODO Auto-generated constructor stub
//	}

	public Enemy(int attackDamage, int health, int lootDropChance) {
		this.attackDamage = attackDamage;
		this.health = health;
		this.lootDropChance = lootDropChance;
	}

	public int getAttackDamage() {
		return attackDamage;
	}

	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLootDropChance() {
		return lootDropChance;
	}

	public void setLootDropChance(int lootDropChance) {
		this.lootDropChance = lootDropChance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + attackDamage;
		result = prime * result + health;
		result = prime * result + lootDropChance;
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
		Enemy other = (Enemy) obj;
		if (attackDamage != other.attackDamage)
			return false;
		if (health != other.health)
			return false;
		if (lootDropChance != other.lootDropChance)
			return false;
		return true;
	}

}
