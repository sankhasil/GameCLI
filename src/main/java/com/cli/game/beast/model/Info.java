/**
 * 
 */
package com.cli.game.beast.model;

import java.time.LocalDateTime;

/**
 * @author Sankha
 *
 */
public class Info implements java.io.Serializable {

	private static final long serialVersionUID = 4972661385204559093L;
	private String author, description, instruction;
	private int iID;
	public LocalDateTime timeIn = LocalDateTime.now();

	public Info(String author, String description, String instruction, int iID) {
		this.author = author;
		this.description = description;
		this.instruction = instruction;
		this.iID = iID;
	}

	@Override
	public String toString() {
		StringBuilder infoBuilder = new StringBuilder();
		infoBuilder.append("\n\tAuthor's name: " + author);
		infoBuilder.append("\n\tIdentification: " + iID);
		infoBuilder.append("\n\tDescription: " + description);
		infoBuilder.append("\n\tInstruction: " + instruction);
		infoBuilder.append("\n\tTime opened: " + timeIn.toString());
		infoBuilder.append("\n\tCopyright 2019, All rights reserved");
		return infoBuilder.toString();
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setInstruction(String instruction) {
		this.instruction = instruction;
	}

	public void setiID(int iID) {
		this.iID = iID;
	}

	public void setTimeIn(LocalDateTime timeIn) {
		this.timeIn = timeIn;
	}

	public String getAuthor() {
		return author;
	}

	public String getDescription() {
		return description;
	}

	public String getInstruction() {
		return instruction;
	}

	public int getiID() {
		return iID;
	}

	public LocalDateTime getTimeIn() {
		return timeIn;
	}

}
