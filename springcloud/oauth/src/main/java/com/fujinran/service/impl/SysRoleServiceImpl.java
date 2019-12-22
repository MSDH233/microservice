package com.fujinran.service.impl;

import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import com.fujinran.mapper.SysRoleMapper;
import com.fujinran.service.SysRoleService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public class SysRoleServiceImpl implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper ;

    @Override
    public List<SysRole> findAllRole() {
        return sysRoleMapper.selectAll();
    }

    @Override
    public SysRole findRole(SysRole sysRole) {
        return sysRoleMapper.selectByPrimaryKey(sysRole);
    }

    @Override
    public boolean insertRole(SysRole sysRole) {
        return sysRoleMapper.insertSelective(sysRole)>0?true:false;
    }

    @Override
    public boolean modifyRole(SysRole sysRole) {
        return sysRoleMapper.updateByPrimaryKey(sysRole)>0?true:false;
    }

    @Override
    public boolean deleteRole(SysRole sysRole) {
        return sysRoleMapper.deleteByPrimaryKey(sysRole)>0?true:false;
    }

    @Override
    public List<SysRole> getRoleByUser(List<SysUser> sysUser){
        if(CollectionUtils.isEmpty(sysUser)){
            return null ;
        }
        return sysRoleMapper.getRoleByUser(sysUser);
    }
}