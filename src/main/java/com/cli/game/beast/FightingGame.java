/**
 * 
 */
package com.cli.game.beast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.filechooser.FileSystemView;

import com.cli.game.beast.engine.FightingLogic;
import com.cli.game.beast.model.Info;
import com.cli.game.constant.strings.GameConstants;

/**
 * @author Sankha
 *
 */
public class FightingGame {

	/**
	 * 
	 */
	public FightingGame() {
		String pwd = System.getProperty("user.dir");
		File infoFile = new File(pwd+"/"+GameConstants.INFO_FILE_LOCATION);
		Info gameInfo = null;

		if (!infoFile.exists()) {
			gameInfo = new Info("Sankha",
					"Simple animation of characters with basic interaction and some AI implementation.",
					"Use the left, right arrow keys to move the character. Z and X to attack.", 20190520);
			try {
				//check the file creation
				infoFile.createNewFile();
				FileOutputStream fileOutputStream = new FileOutputStream(infoFile);
				ObjectOutputStream oOut = new ObjectOutputStream(fileOutputStream);
				oOut.writeObject(gameInfo);
				oOut.close();
				fileOutputStream.close();
			} catch (IOException i) {
				i.printStackTrace();
			}
		} else if (infoFile.exists()) {
			try {
				FileInputStream fileInputStream = new FileInputStream(infoFile);
				ObjectInputStream oIn = new ObjectInputStream(fileInputStream);
				gameInfo = (Info) oIn.readObject();
				oIn.close();
				fileInputStream.close();
			} catch (IOException tracer) {
				tracer.printStackTrace();
			} catch (ClassNotFoundException cNotFound) {
				cNotFound.printStackTrace();

			}
		}
		System.out.println(gameInfo.toString());
		FightingLogic.getInstance();
	}

	public static void exitGame() {
		FightingLogic.getInstance().fScreen.dispose();
	}

}
