package com.fujinran.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by FuJinRan on 2019/12/17.
 */
public class JsonFormat {

    private static final String TIME_FORMAT_1 = "yyyy-MM-dd HH:mm:ss" ;

    private static final String TIME_FORMAT_2 = "yyyy-MM-dd" ;

    private static Gson gson_1 = new GsonBuilder().setDateFormat(TIME_FORMAT_1).create();

    private static Gson gson_2 = new GsonBuilder().setDateFormat(TIME_FORMAT_2).create();

    public static String format(Object o , String TIME_FORMAT){
        if(TIME_FORMAT_1.equals(TIME_FORMAT)){
            return gson_1.toJson(o);
        }else if(TIME_FORMAT_2.equals(TIME_FORMAT)){
            return gson_2.toJson(o);
        }
        return new GsonBuilder().setDateFormat(TIME_FORMAT_2).create().toJson(o);
    }


    public static <T> T fromJson(String o ,Class<T> clazz ,String TIME_FORMAT){
        if(TIME_FORMAT_1.equals(TIME_FORMAT)){
            return gson_1.fromJson(o,clazz);
        }else if(TIME_FORMAT_2.equals(TIME_FORMAT)){
            return gson_2.fromJson(o,clazz);
        }
        return new GsonBuilder().setDateFormat(TIME_FORMAT_2).create().fromJson(o,clazz);
    }

}