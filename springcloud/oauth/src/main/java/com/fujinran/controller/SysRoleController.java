package com.fujinran.controller;

import com.fujinran.domain.SysRole;
import com.fujinran.service.SysRoleService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@RestController
@RequestMapping("sys_role")
public class SysRoleController {
    @Autowired
    private SysRoleService sysRoleService ;

    @RequestMapping("/find")
    public ResultEntity findRole(SysRole sysRole){
        return new ResultEntity(sysRoleService.findRole(sysRole));
    }
    @RequestMapping("/findAll")
    public ResultEntity findAll(){
        return new ResultEntity(sysRoleService.findAllRole());
    }
    @RequestMapping("/insert")
    public ResultEntity insertRole(SysRole sysRole){
        return new ResultEntity(sysRoleService.insertRole(sysRole),null);
    }
}