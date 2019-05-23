/**
 * 
 */
package com.test.cli.engine;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.cli.game.engine.PlayingLogic;
import com.cli.game.engine.exceptions.GameCommandNotSupportedException;
import com.cli.game.engine.exceptions.PlayLogiceException;

/**
 * @author Sankha
 *
 */
public class PlayingLogicTest {
	private PlayingLogic playLogic;
	private Scanner scan;

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test(expected = PlayLogiceException.class)
	public void wrongCommandInputTest() throws PlayLogiceException {
		scan = new Scanner("Wrong Input");
		playLogic = new PlayingLogic(scan, false);
		playLogic.executePlayLogic();
	}
	//FIXME: Need to fix the test
//	@Test(expected = GameCommandNotSupportedException.class)
//	public void wrongGameCommandInputTest() throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException  {
//		Method commandProcessMethod = playLogic.getClass().getDeclaredMethod("processCommand", null);
//		commandProcessMethod.setAccessible(true);
//		scan = new Scanner("h");
//		commandProcessMethod.invoke(playLogic, "");
//	}
	@After
	public void closeAll() {
		scan.close();
	}
	
}
