package jp.co.axa.apidemo.exceptions;

/**
 * @description: exception enum
 * @author: Li
 * @version: v1.0
 */
public enum ExceptionEnum implements BaseErrorInfoInterface {
	//enum of several status
	SUCCESS("2000", "success!"), BODY_NOT_FOUND("4000", "data not found!"), DATA_FORMAT_ERROR("4001", "your input data format is wrong!"),
	MEDIA_TYPE_ERROR("4001", "your content type is wrong!we only accept json format for POST/PUT"),
	NOT_FOUND("4004", "resource not found!"), INTERNAL_SERVER_ERROR("5000", "internal error!"), SERVER_BUSY("5003", "system busy!");

	/**
	 * errorCode
	 */
	private final String resultCode;

	/**
	 * errorMessage
	 */
	private final String resultMsg;

	ExceptionEnum(String resultCode, String resultMsg) {
		this.resultCode = resultCode;
		this.resultMsg = resultMsg;
	}

	@Override
	public String getResultCode() {
		return resultCode;
	}

	@Override
	public String getResultMsg() {
		return resultMsg;
	}
}
