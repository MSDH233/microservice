package com.fujinran.service.impl;

import com.fujinran.domain.SysRoleUser;
import com.fujinran.mapper.SysRoleUserMapper;
import com.fujinran.service.SysRoleUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/19.
 */
@Service
public class SysRoleUserServiceImpl implements SysRoleUserService {

    @Autowired
    private SysRoleUserMapper sysRoleUserMapper ;

    @Override
    public SysRoleUser find(SysRoleUser sysRoleUser) {
        return sysRoleUserMapper.selectByPrimaryKey(sysRoleUser);
    }

    @Override
    public List<SysRoleUser> findAll() {
        return sysRoleUserMapper.selectAll();
    }

    @Override
    public boolean insert(SysRoleUser sysRoleUser) {
        return sysRoleUserMapper.insertSelective(sysRoleUser)>0 ;
    }

    @Override
    public List<SysRoleUser> findByRoleOrUser(SysRoleUser sysRoleUser) {
        if( sysRoleUser == null ){
            return null ;
        }
        if( sysRoleUser.getRoleId() == null  && sysRoleUser.getUserId() == null ){
            return null ;
        }
        return sysRoleUserMapper.findByRoleOrUser(sysRoleUser);
    }
}