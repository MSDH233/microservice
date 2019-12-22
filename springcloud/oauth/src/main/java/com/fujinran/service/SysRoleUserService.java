package com.fujinran.service;

import com.fujinran.domain.SysRoleUser;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/19.
 */
public interface SysRoleUserService {
    public SysRoleUser find(SysRoleUser sysRoleUser);

    public List<SysRoleUser> findAll();

    public boolean insert(SysRoleUser sysRoleUser);

    public List<SysRoleUser> findByRoleOrUser(SysRoleUser sysRoleUser);
}
