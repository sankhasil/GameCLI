/**
 * 
 */
package com.cli.game;

import java.util.Scanner;

import com.cli.game.engine.PlayingLogic;
import com.cli.game.engine.exceptions.PlayLogiceException;

/**
 * @author Sankha
 *
 */
public class Execution {

	/**
	 * @param args
	 */
	static boolean godmode;
	public static void main(String[] args) {
		processCommandLineArguments(args);
		Scanner scan = new Scanner(System.in);
		PlayingLogic play = new PlayingLogic(scan,godmode);
		try {
			play.executePlayLogic();
		} catch (PlayLogiceException e) {
			e.printStackTrace();
		}
	}

	public static void processCommandLineArguments(String... args) {
		for (String arg : args) {
			switch (arg) {
			case "-h":
			case "-H":
			case "-help":
			case "-HELP":
				System.out.println("Write help of the execution");
				System.exit(0);
				break;
			case "-v":
			case "-V":
			case "-version":
			case "-VERSION":
				System.out.println("1.0.0-beta");
				System.exit(0);
				break;
			case "-g":
			case "-G":
			case "-god":
			case "-GOD":
				System.out.println("God Mode is on");
				godmode = true;
				break;

			default:
				break;
			}
		}
	}
}
