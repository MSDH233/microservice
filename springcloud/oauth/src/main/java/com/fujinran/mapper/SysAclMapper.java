package com.fujinran.mapper;

import com.fujinran.domain.SysAcl;
import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface SysAclMapper extends BaseMapper<SysAcl> {
    public SysAcl selectSysAclByNameAndModuleId(SysAcl sysAcl);

    public SysAcl findAclByAclModuleId(SysAcl sysAcl);

    List<SysAcl> getAclByUser(SysUser su);

    List<SysAcl> getAclByRole(SysRole su);

    List<String> getAclUrlByUser(SysUser foundUser);
}