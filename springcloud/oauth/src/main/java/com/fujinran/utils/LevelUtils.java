package com.fujinran.utils;


import org.apache.commons.lang3.StringUtils;

/**
 * Created by FuJinRan on 2019/12/18.
 */
public class LevelUtils {
    private static final String SPEARATOR = "." ;
    private static final String ROOT ="0";

    public static String calculateLevel(String parentLevel , int parentId){
        if(StringUtils.isEmpty(parentLevel)){
            return ROOT ;
        }else{
            return StringUtils.join(parentLevel,SPEARATOR,parentId);
        }
    }

    public static String getRoot (){
        return ROOT ;
    }
}