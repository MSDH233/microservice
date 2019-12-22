package com.fujinran.controller;

import com.fujinran.domain.SysAclModule;
import com.fujinran.domain.SysUser;
import com.fujinran.param.RoleAclTreeParam;
import com.fujinran.service.SysAclModuleService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/18.
 */
@RestController
@RequestMapping("sys_acl_module")
public class SysAclModuleController {

    @Autowired
    private SysAclModuleService sysAclModuleService;

    @RequestMapping("/insert")
    public ResultEntity insertSysModule(SysAclModule sysAclModule) throws Exception{
        return  new ResultEntity(sysAclModuleService.insertAclModule(sysAclModule),sysAclModule) ;
    }

    @RequestMapping("/getTree")
    public ResultEntity getCurretUserAclTree(SysUser su ){
        return new ResultEntity(sysAclModuleService.currentUserAclModuleTree(su));
    }

    @RequestMapping("/getRoleAclTree")
    public ResultEntity getRoleAclTree(RoleAclTreeParam roleAclTreeParam){
        return new ResultEntity(sysAclModuleService.roleAclModuleTree(roleAclTreeParam.getSysUser(),roleAclTreeParam.getSysRole()));
    }
}