package cn.thislx.springboot.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ${description}
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/9/18 20:14
 **/
@Component
public class ValueConfig {

    @Value("${server.port}")
    private String lakala_appId;
}
