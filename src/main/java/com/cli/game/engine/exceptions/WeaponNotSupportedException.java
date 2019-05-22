/**
 * 
 */
package com.cli.game.engine.exceptions;

/**
 * @author Sankha
 *
 */
public class WeaponNotSupportedException extends Exception {

	/**
	 * 
	 */
	public WeaponNotSupportedException() {
	}

	/**
	 * @param message
	 */
	public WeaponNotSupportedException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public WeaponNotSupportedException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public WeaponNotSupportedException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public WeaponNotSupportedException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
