package boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by FuJinRan on 2019/10/2.
 */
@SpringBootApplication
@EnableEurekaClient
@ComponentScan({"controller","mapper","service"})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}