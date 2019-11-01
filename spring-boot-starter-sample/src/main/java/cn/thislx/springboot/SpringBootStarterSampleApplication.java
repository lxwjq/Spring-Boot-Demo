package cn.thislx.springboot;

import cn.thislx.springboot.service.TestService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringBootStarterSampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarterSampleApplication.class, args);
    }


    @Bean
    public CommandLineRunner commandLineRunner(TestService testService) {
        return (args) -> {
            System.out.println(testService.getServiceName());
        };
    }

}
