/**
 * 
 */
package com.cli.game.model.base;

import java.io.Serializable;

/**
 * @author Sankha
 *
 */
public class Armor implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8571735084979859703L;
	private int hitProtection;
	public void setHitProtection(int hitProtection) {
		this.hitProtection = hitProtection;
	}
	public Armor(int hitProtection) {
		this.hitProtection = hitProtection;
	}
	public int getHitProtection() {
		return hitProtection;
	}
	@Override
	public String toString() {
		return "Armor [hitProtection=" + hitProtection + "]";
	}
	
	
}
