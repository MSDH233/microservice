package com.fujinran.mapper;

import com.fujinran.domain.SysRoleUser;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

public interface SysRoleUserMapper extends BaseMapper<SysRoleUser> {
    public List<SysRoleUser> findByRoleOrUser(SysRoleUser sysRoleUser);
}