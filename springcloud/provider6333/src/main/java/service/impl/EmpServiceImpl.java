package service.impl;

import entities.Emp;
import mapper.EmpMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.EmpService;

import java.util.List;

/**
 * Created by FuJinRan on 2019/10/3.
 */
@Service
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper ;

    @Override
    public Emp get(Integer empId) {
        return empMapper.get(empId);
    }

    @Override
    public boolean set(Emp emp) {
        return empMapper.set(emp);
    }

    @Override
    public List<Emp> selectAll() {
        return empMapper.selectAll();
    }

}