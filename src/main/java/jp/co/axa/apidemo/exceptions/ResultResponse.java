package jp.co.axa.apidemo.exceptions;

import com.alibaba.fastjson.JSONObject;

/**
 * @description: customized response
 * @author: Li
 * @version: v1.0
 */
public class ResultResponse {
	/**
	 * code
	 */
	private String code;

	/**
	 * message
	 */
	private String message;

	/**
	 * result
	 */
	private Object result;

	public ResultResponse() {
	}

	public ResultResponse(BaseErrorInfoInterface errorInfo) {
		this.code = errorInfo.getResultCode();
		this.message = errorInfo.getResultMsg();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	/**
	 * success
	 *
	 * @return
	 */
	public static ResultResponse success() {
		return success(null);
	}

	/**
	 * success
	 * 
	 * @param data
	 * @return
	 */
	public static ResultResponse success(Object data) {
		ResultResponse rb = new ResultResponse();
		rb.setCode(ExceptionEnum.SUCCESS.getResultCode());
		rb.setMessage(ExceptionEnum.SUCCESS.getResultMsg());
		rb.setResult(data);
		return rb;
	}

	/**
	 * error
	 */
	public static ResultResponse error(BaseErrorInfoInterface errorInfo) {
		ResultResponse rb = new ResultResponse();
		rb.setCode(errorInfo.getResultCode());
		rb.setMessage(errorInfo.getResultMsg());
		rb.setResult(null);
		return rb;
	}

	/**
	 * error
	 */
	public static ResultResponse error(String code, String message) {
		ResultResponse rb = new ResultResponse();
		rb.setCode(code);
		rb.setMessage(message);
		rb.setResult(null);
		return rb;
	}

	/**
	 * error
	 */
	public static ResultResponse error(String message) {
		ResultResponse rb = new ResultResponse();
		rb.setCode("-1");
		rb.setMessage(message);
		rb.setResult(null);
		return rb;
	}

	@Override
	public String toString() {
		return JSONObject.toJSONString(this);
	}

}
