package com.fujinran.mapper;

import com.fujinran.domain.SysAclModule;
import tk.mybatis.mapper.common.BaseMapper;

public interface SysAclModuleMapper extends BaseMapper<SysAclModule> {
    SysAclModule selectAclModuleByNameAndParentId(SysAclModule sysAclModule);
}