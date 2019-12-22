package com.fujinran.mapper;

import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface SysRoleMapper extends BaseMapper<SysRole> {
    List<SysRole> getRoleByUser(List<SysUser> sysUser);
}