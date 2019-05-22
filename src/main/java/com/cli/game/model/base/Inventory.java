/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sankha
 *
 */
public class Inventory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5193286130353539841L;
	private List<Potion> listOfPotions = new ArrayList<>();
	private List<Weapon> listOfWeapons = new ArrayList<>();
	private List<Armor> listOfArmor = new ArrayList<>();
//
//	public Inventory() {
//		// TODO Auto-generated constructor stub
//	}
//TODO add other items
	public List<Potion> getListOfPotions() {
		return listOfPotions;
	}

	public void setListOfPotions(List<Potion> listOfPotions) {
		this.listOfPotions = listOfPotions;
	}

	public List<Weapon> getListOfWeapons() {
		return listOfWeapons;
	}

	public void setListOfWeapons(List<Weapon> listOfWeapons) {
		this.listOfWeapons = listOfWeapons;
	}

	public List<Armor> getListOfArmor() {
		return listOfArmor;
	}

	public void setListOfArmor(List<Armor> listOfArmor) {
		this.listOfArmor = listOfArmor;
	}

	public boolean addPotionToInventory(Potion potion) {
		return listOfPotions.add(potion);
	}
	public boolean addWeaponToInventory(Weapon weapon) {
		return listOfWeapons.add(weapon);
	}
	public boolean addArmorToInventory(Armor armor) {
		return listOfArmor.add(armor);
	}

	@Override
	public String toString() {
		return "Inventory [Potions=" + listOfPotions.toString() + ", Weapons=" + listOfWeapons.toString() + ", Armors="
				+ listOfArmor != null? listOfArmor.size() +"" : "No Armors"  + "]";
	}
	
}
