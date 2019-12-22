package com.fujinran.controller;

import com.fujinran.domain.SysUser;
import com.fujinran.param.LoginParam;
import com.fujinran.service.SysUserService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@RestController
@RequestMapping("sys_user")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService ;

    @RequestMapping("/insert")
    public ResultEntity insertUser(SysUser sysUser){
        return new ResultEntity(sysUserService.insertUser(sysUser),null);
    }

    @RequestMapping("/find")
    public ResultEntity findUser(SysUser sysUser){
        return new ResultEntity(sysUserService.findUser(sysUser));
    }

    @RequestMapping("/findAll")
    public ResultEntity findAllUser(){
        return new ResultEntity(sysUserService.findAllUser());
    }

    @RequestMapping("/login")
    public ResultEntity login(LoginParam loginParam) throws Exception{
        return new ResultEntity(sysUserService.login(loginParam));
    }
}