package com.club.club_manager.commom;

import com.club.club_manager.exception.MyException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: lhz
 * @date: 2020/10/26
 **/
@ControllerAdvice
public class CommonException {
    @ExceptionHandler(MyException.class)
    public CommonResult<String> exceptionHandler(MyException e) {
        return CommonResult.failed(CommonResult.RetCode.UNAUTHORIZED,e.getMessage(),null);
    }

}
