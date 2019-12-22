package com.fujinran.service.impl;

import com.fujinran.domain.SysAcl;
import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import com.fujinran.mapper.SysAclMapper;
import com.fujinran.service.SysAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public class SysAclServiceImpl implements SysAclService {

    @Autowired
    private SysAclMapper sysAclMapper;

    @Override
    public List<SysAcl> findAllAcl() {
        return sysAclMapper.selectAll();
    }

    @Override
    public SysAcl findAcl(SysAcl sysAcl) {
        return null;
    }

    @Override
    public SysAcl findAclByAclModuleId(SysAcl sysAcl){
        if(sysAcl.getAclModuleId()==null){
            return null;
        }
        return sysAclMapper.findAclByAclModuleId(sysAcl);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertAcl(SysAcl sysAcl) throws Exception{
        checkAcl(sysAcl);
        return sysAclMapper.insert(sysAcl) > 0?true:false;
    }

    public boolean checkAcl(SysAcl sysAcl) throws Exception{
        SysAcl foundAcl = sysAclMapper.selectSysAclByNameAndModuleId(sysAcl);
        if(foundAcl != null){
            throw new Exception("当前权限模块下已存在相同权限名");
        }
        return false ;
    }

    @Override
    public boolean modifyAcl(SysAcl sysAcl) {
        return false;
    }

    @Override
    public boolean deleteAcl(SysAcl sysAcl) {
        return false;
    }

    @Override
    public List<SysAcl> getAclByUser(SysUser su){
        if(su == null ){
            return null ;
        }
        return sysAclMapper.getAclByUser(su);
    }
    @Override
    public List<SysAcl> getAclByRole(SysRole sr){
        if(sr == null ){
            return null ;
        }
        return sysAclMapper.getAclByRole(sr);
    }

    @Override
    public List<String> getAclUrlByUser(SysUser foundUser) {
        return sysAclMapper.getAclUrlByUser(foundUser);
    }
}