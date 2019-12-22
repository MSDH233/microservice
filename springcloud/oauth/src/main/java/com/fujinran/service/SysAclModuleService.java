package com.fujinran.service;

import com.fujinran.domain.SysAclModule;
import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import com.fujinran.dto.SysAclModuleDto;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/18.
 */
public interface SysAclModuleService {
    public List<SysAclModule> findAllAclModule();
    public SysAclModule findAclModule(SysAclModule sysAclModule);
    public boolean  insertAclModule(SysAclModule sysAclModule) throws Exception;
    public boolean  modifyAclModule(SysAclModule sysAclModule);
    public boolean  deleteAclModule(SysAclModule sysAclModule);
    public List<SysAclModuleDto> currentUserAclModuleTree(SysUser su);
    public List<SysAclModuleDto> roleAclModuleTree(SysUser su, SysRole sr);
}
