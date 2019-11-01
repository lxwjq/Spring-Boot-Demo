package cn.thislx.springboot.config;

import cn.thislx.springboot.service.TestService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Starter配置文件类
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/9/18 14:56
 **/
@Configuration
@ConditionalOnProperty(prefix = "thislx.starter", name = "enabled", havingValue = "true")
public class TestServiceAutoConfiguration {
    @Bean
    public TestService testService() {
        return new TestService();
    }

}
