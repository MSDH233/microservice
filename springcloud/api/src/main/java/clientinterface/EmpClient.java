package clientinterface;

import entities.Emp;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by FuJinRan on 2019/10/8.
 */
@FeignClient(value = "microservice-provider",fallbackFactory = EmpClientFallback.class)
public interface EmpClient {
    @RequestMapping(value = "/emp/get")
    public Emp getEmp(@RequestParam("empId") Integer empId) ;


    @RequestMapping("/emp/getMessage")
    public String test();
}
