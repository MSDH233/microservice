package com.fujinran.controller;

import com.fujinran.domain.SysAcl;
import com.fujinran.param.OauthCheckParam;
import com.fujinran.service.SysAclService;
import com.fujinran.utils.bean.ResultEntity;
import com.fujinran.utils.filter.TokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@RestController
@RequestMapping("sys_acl")
public class SysAclContoller {

    @Autowired
    private SysAclService sysAclService ;


    @Autowired
    private TokenFilter tokenFilter ;

    @RequestMapping("/find")
    public ResultEntity findAcl(SysAcl sysAcl){
        return new ResultEntity(sysAclService.findAcl(sysAcl));
    }
    @RequestMapping("/findAll")
    public ResultEntity findAll(){
        return new ResultEntity(sysAclService.findAllAcl());
    }
    @RequestMapping("/find_by_aclModule")
    public ResultEntity findByAclModule(SysAcl sysAcl){
        return new ResultEntity(sysAclService.findAclByAclModuleId(sysAcl));
    }

    @RequestMapping("/insert")
    public ResultEntity insertAcl(SysAcl sysAcl) throws Exception{
        return new ResultEntity(sysAclService.insertAcl(sysAcl),null);
    }


    @RequestMapping(value = "/checkAcl", method = RequestMethod.POST)
    public ResultEntity checkOauth(@RequestBody OauthCheckParam oauthCheckParam) throws Exception {
        String token = oauthCheckParam.getToken();
        String requestURI = oauthCheckParam.getRquestURI();
        ResultEntity resultEntity = tokenFilter.check(oauthCheckParam);
        return resultEntity ;
    }
}