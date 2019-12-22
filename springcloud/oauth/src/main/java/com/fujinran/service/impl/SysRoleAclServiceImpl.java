package com.fujinran.service.impl;

import com.fujinran.domain.SysRoleAcl;
import com.fujinran.mapper.SysRoleAclMapper;
import com.fujinran.service.SysRoleAclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@Service
public class SysRoleAclServiceImpl implements SysRoleAclService {

    @Autowired
    private SysRoleAclMapper sysRoleAclMapper;

    @Override
    public SysRoleAcl find(SysRoleAcl sysRoleAcl) {
        return sysRoleAclMapper.selectByPrimaryKey(sysRoleAcl);
    }

    @Override
    public List<SysRoleAcl> findAll() {
        return sysRoleAclMapper.selectAll();
    }

    @Override
    public boolean insert(SysRoleAcl sysRoleAcl) {
        return sysRoleAclMapper.insertSelective(sysRoleAcl)>0;
    }

    @Override
    public List<SysRoleAcl> findByRoleOrAcl(SysRoleAcl sysRoleAcl) {
        if(sysRoleAcl == null ){
            return null ;
        }
        if(sysRoleAcl.getRoleId()==null && sysRoleAcl.getAclId()==null){
            return null ;
        }
        return sysRoleAclMapper.findByRoleOrAcl(sysRoleAcl);
    }
}