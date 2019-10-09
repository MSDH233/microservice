package controller;

import clientinterface.EmpClient;
import entities.Emp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by FuJinRan on 2019/10/8.
 */
@RequestMapping("/order")
@RestController
public class OrderController {

   @Autowired
   private EmpClient empClient ;

   @RequestMapping("/getEmp/{empId}")
   public Emp test(@PathVariable Integer empId){
       return  empClient.getEmp(empId);
   }

}