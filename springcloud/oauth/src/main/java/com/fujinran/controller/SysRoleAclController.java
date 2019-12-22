package com.fujinran.controller;

import com.fujinran.domain.SysRoleAcl;
import com.fujinran.service.SysRoleAclService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@RestController
@RequestMapping("sys_role_acl")
public class SysRoleAclController {

    @Autowired
    private SysRoleAclService sysRoleAclService ;

    @RequestMapping("/find")
    public ResultEntity find(SysRoleAcl SysRoleAcl){
        return new ResultEntity(sysRoleAclService.find(SysRoleAcl));
    }

    @RequestMapping("/findAll")
    public ResultEntity findAll(){
        return new ResultEntity(sysRoleAclService.findAll());
    }

    @RequestMapping("/insert")
    public ResultEntity insert(SysRoleAcl SysRoleAcl){
        return new ResultEntity(sysRoleAclService.insert(SysRoleAcl));
    }

    @RequestMapping("/findByRoleOrUser")
    public ResultEntity findByRoleOrAcl(SysRoleAcl SysRoleAcl){
        return new ResultEntity(sysRoleAclService.findByRoleOrAcl(SysRoleAcl));
    }
}