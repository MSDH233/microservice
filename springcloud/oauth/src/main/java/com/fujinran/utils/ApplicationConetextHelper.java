package com.fujinran.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Component
public class ApplicationConetextHelper implements ApplicationContextAware{

    private static  ApplicationContext applicationContext ;

    public ApplicationConetextHelper(){
        super();
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
            this.applicationContext = applicationContext ;
    }

    public ApplicationContext getApplicationContext(){
        return this.applicationContext ;
    }

    public static<T> T popBean(Class<T> clazz){

        if( applicationContext == null){
            return null ;
        }

        return  applicationContext.getBean(clazz);
    }
    public static<T> T popBean(String name , Class<T> clazz){

        if( applicationContext == null){
            return null ;
        }
        return  applicationContext.getBean(name , clazz);
    }
}