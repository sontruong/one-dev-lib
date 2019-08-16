/**
 * 
 */
package com.ones.user;

import java.lang.reflect.UndeclaredThrowableException;

import javax.security.auth.login.LoginException;
import javax.validation.ConstraintViolationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.common.exceptions.UnauthorizedUserException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.ones.dto.ErrorMessage;
import com.ones.exception.ContentException;
import com.ones.exception.NotFoundException;
import com.ones.exception.NotLoginException;
import com.ones.exception.PermissionDenyException;
import com.ones.utils.AppUtils;
import com.ones.utils.ApplicationMessage;
import com.ones.utils.StringUtils;

/**
 * @author son.truong Apr 13, 2017 11:03:56 PM
 */
@ControllerAdvice
public class GlobalExceptionController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(BindException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleException(BindException exception) {
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setMessage(ApplicationMessage.VALUE_NOT_RIGHT);
		errorMessage.setViolations(exception.getAllErrors());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	// @ExceptionHandler(HttpMessageNotReadableException.class)
	// public @ResponseBody ResponseEntity<ErrorMessage>
	// handleException(HttpMessageNotReadableException exception) {
	// ErrorMessage errorMessage = new ErrorMessage();
	// errorMessage.setMessage(ApplicationMessage.VALUE_NOT_RIGHT);
	//
	// return new ResponseEntity<ErrorMessage>(errorMessage,
	// HttpStatus.BAD_REQUEST);
	// }

	@ExceptionHandler(Exception.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleException(Exception exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.INTERNAL_SERVER_ERROR);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleException(IllegalArgumentException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.INTERNAL_SERVER_ERROR);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UndeclaredThrowableException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleException(UndeclaredThrowableException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.BANDWIDTH_LIMIT_EXCEEDED);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BANDWIDTH_LIMIT_EXCEEDED);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleSException(
			MissingServletRequestParameterException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.REQUEST_PARAM_REQUIRE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ServletRequestBindingException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleSException(ServletRequestBindingException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.REQUEST_PARAM_REQUIRE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleTypeException(
			MethodArgumentTypeMismatchException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.WRONG_PARAM_TYPE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NumberFormatException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleNFException(NumberFormatException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.WRONG_PARAM_TYPE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ContentException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(ContentException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(exception.getId());
		if (null == StringUtils.isEmpty(errorMessage.getCode())) {
			errorMessage.setCode(exception.getMessage());
		}
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ConstraintViolationException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(ConstraintViolationException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage();
		errorMessage.setCode(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(MethodArgumentNotValidException exception) {
		AppUtils.printStackTrace(exception, logger);
		BindingResult bindingResult = exception.getBindingResult();
		ErrorMessage errorMessage = new ErrorMessage();
		if (null != bindingResult && bindingResult.hasFieldErrors()) {
			FieldError error = bindingResult.getFieldError();
			errorMessage.setCode(error.getDefaultMessage());
		} else {
			errorMessage.setCode(exception.getMessage());
		}
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(HttpMessageNotReadableException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.REQUEST_BODY_REQUIRE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleNotFoundException(NotFoundException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.NOT_FOUND_EXCEPTION);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(PermissionDenyException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(PermissionDenyException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.NOT_ACCEPTABLE);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.METHOD_NOT_ALLOWED);
	}

	@ExceptionHandler(UnauthorizedUserException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(UnauthorizedUserException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.LOGIN_FAIL);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(LoginException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(LoginException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.LOGIN_FAIL);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(NotLoginException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(NotLoginException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.LOGIN_FAIL);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

	@ExceptionHandler(RuntimeException.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(RuntimeException exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.INTERNAL_SERVER_ERROR);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(OAuth2Exception.class)
	public @ResponseBody ResponseEntity<ErrorMessage> handleCustomException(OAuth2Exception exception) {
		AppUtils.printStackTrace(exception, logger);
		ErrorMessage errorMessage = new ErrorMessage(ApplicationMessage.LOGIN_FAIL);
		errorMessage.setMessage(exception.getMessage());
		return new ResponseEntity<ErrorMessage>(errorMessage, HttpStatus.UNAUTHORIZED);
	}

}
