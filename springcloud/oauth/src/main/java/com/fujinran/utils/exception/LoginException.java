package com.fujinran.utils.exception;

import javax.servlet.ServletException;

/**
 * Created by FuJinRan on 2019/12/20.
 */
public class LoginException  extends ServletException{

    public LoginException(){
        super();
    }
    public LoginException(String message){
        super(message);
    }
    public LoginException(String message,Throwable throwable){
        super(message,throwable);
    }
}