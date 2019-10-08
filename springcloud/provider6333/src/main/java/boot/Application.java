package boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by FuJinRan on 2019/10/2.
 */
@SpringBootApplication
@ComponentScan({"controller","service"})
@MapperScan({"mapper"})
@EnableDiscoveryClient
public class Application {
    public static void main(String[] args) {

        SpringApplication sa  =  new SpringApplicationBuilder(Application.class).build(args) ;

        ApplicationContext  ac = sa.run() ;

    }
}