/**
 * 
 */
package com.cli.game.engine.exceptions;

/**
 * @author Sankha
 *
 */
public class SpecialPowerNotSupportedException extends Exception {

	public SpecialPowerNotSupportedException() {
		super();
	}

	public SpecialPowerNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SpecialPowerNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	public SpecialPowerNotSupportedException(String message) {
		super(message);
	}

	public SpecialPowerNotSupportedException(Throwable cause) {
		super(cause);
	}

}
