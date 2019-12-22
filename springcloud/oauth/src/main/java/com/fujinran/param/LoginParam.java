package com.fujinran.param;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@Data
public class LoginParam  implements Serializable{
    private String keyword ;
    private String password ;
}