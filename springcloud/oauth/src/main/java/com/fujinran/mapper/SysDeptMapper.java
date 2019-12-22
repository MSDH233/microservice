package com.fujinran.mapper;

import com.fujinran.domain.SysDept;
import org.apache.ibatis.annotations.Mapper;
import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface SysDeptMapper extends BaseMapper<SysDept> {
    public SysDept selectByDeptNameAndParentId(SysDept sysDept);
    public int updateChildDept(SysDept sysDept);
}