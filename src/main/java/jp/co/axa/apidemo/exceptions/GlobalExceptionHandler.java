package jp.co.axa.apidemo.exceptions;

import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import lombok.extern.slf4j.Slf4j;

/**
 * @description: Global Exception Handler
 * @author: Li
 * @version: v1.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	/**
	 * biz exception handler
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = BizException.class)
	@ResponseBody
	public ResultResponse bizExceptionHandler(HttpServletRequest req, BizException e) {
		logger.error("biz exception！reason：", e.getErrorMsg());
		return ResultResponse.error(e.getErrorCode(), e.getErrorMsg());
	}

	/**
	 * data not found
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(HttpServletRequest req, NoSuchElementException e) {
		logger.error("data not found！reason:", e);
		return ResultResponse.error(ExceptionEnum.BODY_NOT_FOUND);
	}
	/**
	 * deleteID not found
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(EmptyResultDataAccessException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(HttpServletRequest req, EmptyResultDataAccessException e) {
		logger.error("data not found！reason:", e);
		return ResultResponse.error(ExceptionEnum.BODY_NOT_FOUND);
	}
	
	/**
	 * data not found
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(HttpServletRequest req, MethodArgumentTypeMismatchException e) {
		logger.error("data format error！reason:", e);
		return ResultResponse.error(ExceptionEnum.DATA_FORMAT_ERROR);
	}
	/**
	 * media type not support 
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
	@ResponseBody
	public ResultResponse exceptionHandler(HttpServletRequest req, HttpMediaTypeNotSupportedException e) {
		logger.error("media type not support！reason:", e);
		return ResultResponse.error(ExceptionEnum.MEDIA_TYPE_ERROR);
	}
	
	
	/**
	 * others
	 * 
	 * @param req
	 * @param e
	 * @return
	 */
	@ExceptionHandler(value = Exception.class)
	@ResponseBody
	public ResultResponse exceptionHandler(HttpServletRequest req, Exception e) {
		logger.error("other exceptions！reason:", e);
		return ResultResponse.error(ExceptionEnum.INTERNAL_SERVER_ERROR);
	}
}
