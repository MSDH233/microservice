package com.fujinran.service;

import com.fujinran.domain.SysDept;
import com.fujinran.dto.DeptLevelDto;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
public interface SysDeptService {
    public List<SysDept> findAllDept();
    public SysDept findDept(SysDept sysdept);
    public boolean  insertDept(SysDept SysDept) throws Exception;
    public boolean  modifyDept(SysDept SysDept) throws Exception;
    public boolean  deleteDept(SysDept SysDept);
    public List<DeptLevelDto> deptTree();
}
