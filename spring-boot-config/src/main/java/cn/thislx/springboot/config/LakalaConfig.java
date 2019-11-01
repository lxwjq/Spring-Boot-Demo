package cn.thislx.springboot.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 读取wechat配置文件
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/9/18 15:35
 **/
@Data
@Component
@ConfigurationProperties(prefix = "lakala")
public class LakalaConfig {

    private String appId;

    private String lakala_appId;
}
