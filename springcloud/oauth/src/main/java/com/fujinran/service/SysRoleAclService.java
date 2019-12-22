package com.fujinran.service;

import com.fujinran.domain.SysRoleAcl;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/19.
 */
public interface SysRoleAclService {
    SysRoleAcl find(SysRoleAcl SysRoleAcl);

    List<SysRoleAcl> findAll();

    boolean insert(SysRoleAcl SysRoleAcl);

    List<SysRoleAcl> findByRoleOrAcl(SysRoleAcl SysRoleAcl);
}
