package com.fujinran.controller;

import com.fujinran.domain.SysDept;
import com.fujinran.dto.DeptLevelDto;
import com.fujinran.service.SysDeptService;
import com.fujinran.utils.bean.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by FuJinRan on 2019/12/17.
 */
@RestController
@RequestMapping("/sys_dept")
public class SysDeptController {

    @Autowired
    private SysDeptService sysDeptService ;

    @RequestMapping("/find")
    public ResultEntity findDept(SysDept sysDept){
        return new ResultEntity(sysDeptService.findDept(sysDept));
    }
    @RequestMapping("/findAll")
    public ResultEntity findAll(){
        return new ResultEntity(sysDeptService.findAllDept());
    }
    @RequestMapping("getTree")
    public ResultEntity getTree(){
        List<DeptLevelDto> deptLevelDtoList = sysDeptService.deptTree();
        return new ResultEntity(deptLevelDtoList);
    }
    @RequestMapping("/insert")
    public ResultEntity insertDept(SysDept sysDept) throws Exception{
        return new ResultEntity(sysDeptService.insertDept(sysDept),null);
    }

    @RequestMapping("/modify")
    public ResultEntity modifyDept(SysDept sysDept) throws Exception {
        return new ResultEntity(sysDeptService.modifyDept(sysDept),sysDept);
    }
}