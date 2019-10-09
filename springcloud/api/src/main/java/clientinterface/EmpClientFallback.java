package clientinterface;

import entities.Emp;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * Created by FuJinRan on 2019/10/8.
 */
@Component
public class EmpClientFallback implements FallbackFactory<EmpClient> {

    @Override
    public EmpClient create(Throwable throwable) {
        return new EmpClient() {
            @Override
            public Emp getEmp(Integer empId) {
                return Emp.builder().empName("获取用户是失败").empId(empId).build();
            }

            @Override
            public String test() {
                return "系统错误";
            }
        };
    }
}