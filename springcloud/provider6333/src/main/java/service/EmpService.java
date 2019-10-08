package service;

import entities.Emp;

import java.util.List;

/**
 * Created by FuJinRan on 2019/10/3.
 */
public interface EmpService {

    public Emp get(Integer empId) ;

    public boolean set(Emp emp) ;

    public List<Emp> selectAll() ;

}