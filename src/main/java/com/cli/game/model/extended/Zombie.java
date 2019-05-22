/**
 * 
 */
package com.cli.game.model.extended;

import java.io.Serializable;

import com.cli.game.model.base.Enemy;

/**
 * @author Sankha
 *
 */
public class Zombie extends Enemy implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1688989999348327033L;
	private int convertDeadToZombieChance;
//	public Zombie() {
//	}
	/**
	 * @param attackDamage
	 * @param health
	 * @param lootDropChance
	 */
	public Zombie(int attackDamage, int health, int lootDropChance,int convertDeadToZombieChance) {
		super(attackDamage, health, lootDropChance);
		this.convertDeadToZombieChance = convertDeadToZombieChance;
	}
	public int getConvertDeadToZombieChance() {
		return convertDeadToZombieChance;
	}
	public void setConvertDeadToZombieChance(int convertDeadToZombieChance) {
		this.convertDeadToZombieChance = convertDeadToZombieChance;
	}

	
}
