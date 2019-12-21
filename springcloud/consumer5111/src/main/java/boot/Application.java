package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by FuJinRan on 2019/10/8.
 */
@EnableEurekaClient
@SpringBootApplication
@EnableFeignClients("clientinterface")
@ComponentScan({"controller","clientinterface"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}