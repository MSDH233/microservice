package com.fujinran.mapper;

import com.fujinran.domain.SysRoleAcl;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface SysRoleAclMapper extends BaseMapper<SysRoleAcl> {
    List<SysRoleAcl> findByRoleOrAcl(SysRoleAcl sysRoleAcl);
}