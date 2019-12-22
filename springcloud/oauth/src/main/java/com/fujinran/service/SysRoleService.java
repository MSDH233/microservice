package com.fujinran.service;

import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public interface SysRoleService {
    public List<SysRole> findAllRole();
    public SysRole findRole(SysRole sysRole);
    public boolean  insertRole(SysRole sysRole);
    public boolean  modifyRole(SysRole sysRole);
    public boolean  deleteRole(SysRole sysRole);

    List<SysRole> getRoleByUser(List<SysUser> sysUser);
}
