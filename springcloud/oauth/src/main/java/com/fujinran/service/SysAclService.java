package com.fujinran.service;

import com.fujinran.domain.SysAcl;
import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@Service
public interface SysAclService {
    public List<SysAcl> findAllAcl();
    public SysAcl findAcl(SysAcl sysAcl);
    public boolean  insertAcl(SysAcl sysAcl) throws Exception;
    public boolean  modifyAcl(SysAcl sysAcl);
    public boolean  deleteAcl(SysAcl sysAcl);
    public SysAcl findAclByAclModuleId(SysAcl sysAcl);

    List<SysAcl> getAclByUser(SysUser su);

    List<SysAcl> getAclByRole(SysRole sr);

    List<String> getAclUrlByUser(SysUser foundUser);
}
