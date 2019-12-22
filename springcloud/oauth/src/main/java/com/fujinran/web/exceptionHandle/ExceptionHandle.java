package com.fujinran.web.exceptionHandle;

import com.fujinran.utils.bean.ResultEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Created by FuJinRan on 2019/12/20.
 */
@ControllerAdvice
public class ExceptionHandle {

    @ExceptionHandler({Exception.class})
    public ResultEntity Exception(Exception exception){
        return  ResultEntity.builder().
                data(exception.getCause()).
                message(exception.getMessage()).
                status("200").
                build();
    }

}