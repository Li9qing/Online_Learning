package edu.hubu.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;

/**
 * @author Mr.M
 * @version 1.0
 * @description
 * @date 2023/2/12 17:01
 */
@Slf4j
@ControllerAdvice
//@RestControllerAdvice
public class GlobalExceptionHandler {

    //对项目的自定义异常类型进行处理
    @ResponseBody
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse customException(CustomException e){

        //记录异常
        log.error("系统异常{}", e.getErrMessage(),e);
        //..

        //解析出异常信息
        String errMessage = e.getErrMessage();
        RestErrorResponse restErrorResponse = new RestErrorResponse(errMessage);
        return restErrorResponse;
    }


    @ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(Exception e){

        //记录异常
        log.error("系统异常：{}",e.getMessage(),e);

        //解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(CommonErrorEnum.UNKOWN_ERROR.getErrMessage());
        return restErrorResponse;
    }


    // 解析校验异常
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorResponse exception(MethodArgumentNotValidException e){

        BindingResult bindingResult = e.getBindingResult();

        // 存储错误信息
        ArrayList<String> errors = new ArrayList<>();
        bindingResult.getFieldErrors().stream().forEach( item -> {
            errors.add(item.getDefaultMessage());
        });

        String errorMessage = StringUtils.join(errors, ", ");

        //记录异常
//        log.error("系统异常：{}",e.getMessage(), errorMessage);
        log.error("系统异常：" + errorMessage);

        //解析出异常信息
        RestErrorResponse restErrorResponse = new RestErrorResponse(errorMessage);
        return restErrorResponse;
    }


}

