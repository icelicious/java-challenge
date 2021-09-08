package jp.co.axa.apidemo.exceptions;

/**
 * @description: interface
 * @author: Li
 * @version: v1.0
 */
public interface BaseErrorInfoInterface {

	/**
	 * error code
	 * 
	 * @return
	 */
	String getResultCode();

	/**
	 * error message
	 * 
	 * @return
	 */
	String getResultMsg();
}
