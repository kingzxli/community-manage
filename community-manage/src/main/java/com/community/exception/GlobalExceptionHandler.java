package com.community.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.community.entity.common.Result;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;

/**
 * @ClassName: GlobalExceptionHandler
 * @Description: 全局异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */   
    @ExceptionHandler(CustomException.class)
	public Object customException(CustomException e) {
		return new Result<>(e.getCode(), e.getMsg());
	}

    /**
     * 方法参数校验
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Object handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error(e.getMessage(), e);
        return new Result<>(400, e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    public Object handleValidationException(ValidationException e) {
        logger.error(e.getMessage(), e);
        return new Result<>(400, e.getCause().getMessage());
    }

    /**
     * ConstraintViolationException
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Object handleConstraintViolationException(ConstraintViolationException e) {
        logger.error(e.getMessage(), e);
        return new Result<>(400, e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Object handlerNoFoundException(Exception e) {
        logger.error(e.getMessage(), e);
        return new Result<>(404, "路径不存在，请检查路径是否正确");
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public Object handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return new Result<>(400, "数据重复，请检查后提交");
    }

}