package com.fujinran.controller;

import com.fujinran.domain.SysRoleUser;
import com.fujinran.service.SysRoleUserService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@RestController
@RequestMapping("sys_role_user")
public class SysRoleUserController {

    @Autowired
    private SysRoleUserService sysRoleUserService;

    @RequestMapping("/find")
    public ResultEntity find(SysRoleUser sysRoleUser){
        return new ResultEntity(sysRoleUserService.find(sysRoleUser));
    }

    @RequestMapping("/findAll")
    public ResultEntity findAll(){
        return new ResultEntity(sysRoleUserService.findAll());
    }

    @RequestMapping("/insert")
    public ResultEntity insert(SysRoleUser sysRoleUser){
        return new ResultEntity(sysRoleUserService.insert(sysRoleUser));
    }

    @RequestMapping("/findByRoleOrUser")
    public ResultEntity findByRoleOrUser(SysRoleUser sysRoleUser){
        return new ResultEntity(sysRoleUserService.findByRoleOrUser(sysRoleUser));
    }
}