package controller;

import entities.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.impl.EmpServiceImpl;

/**
 * Created by FuJinRan on 2019/10/3.
 */
@RestController(value = "/emp")
public class EmpController {

    @Autowired
    private EmpServiceImpl empService ;


    @Value("${server.port}")
    private String port ;

    @RequestMapping(value = "/get")
    public Emp getEmp(Integer empId){
       return empService.get(empId);
    }


    @RequestMapping("/getMessage")
    public String test(){
        return port ;
    }
}