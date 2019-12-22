package com.fujinran.mapper;

import com.fujinran.domain.SysRole;
import com.fujinran.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    public SysUser findUserByKeyword(String keyword);
    public List<SysUser> getUserByRole(SysRole sysRole);
}