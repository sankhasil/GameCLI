/**
 * 
 */
package com.cli.game.model.extended;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.cli.game.constant.enums.Gender;
import com.cli.game.model.base.Armor;
import com.cli.game.model.base.Character;
import com.cli.game.model.base.Inventory;
import com.cli.game.model.base.SpecialPower;
import com.cli.game.model.base.Weapon;

/**
 * @author Sankha
 *
 */
public class Hero extends Character implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4579698622624637500L;
	private Inventory inventory;
	private List<SpecialPower> specialPowerList;
	private short level = 1;
	private Weapon weapon;
	private Armor armor;

//	public Hero() {
//	}

	/**
	 * @param name
	 * @param gender
	 * @param health
	 * @param attackDamage
	 */
	public Hero(String name, Gender gender, int health, int attackDamage, Inventory inventory,
			List<SpecialPower> specialPowerList, Weapon weapon, Armor armor) {
		super(name, gender, health, attackDamage);
		this.inventory = inventory;
		this.specialPowerList = specialPowerList;
		this.weapon = weapon;
		this.armor = armor;
	}

	public Inventory getInventory() {
		return inventory;
	}

	public short getLevel() {
		return level;
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public Armor getArmor() {
		return armor;
	}

	public void setLevel(short level) {
		this.level = level;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void setArmor(Armor armor) {
		this.armor = armor;
	}

	public List<SpecialPower> getSpecialPowerList() {
		return specialPowerList;
	}

	public boolean addSpcialPower(SpecialPower specialPower) {
		if (specialPowerList == null)
			specialPowerList = new ArrayList<>();

		if (specialPowerList.contains(specialPower))
			return false;

		return this.specialPowerList.add(specialPower);
	}

	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}

	public void setSpecialPowerList(List<SpecialPower> specialPowerList) {
		this.specialPowerList = specialPowerList;
	}

	@Override
	public String toString() {
		return "Hero, level=" + level + ", weapon=" + weapon.getType().getValue().toUpperCase() + ", Name="
				+ super.getName() + ", Inventory=" + inventory.toString() + ", HP="+getHealth()+"]";
	}

}
