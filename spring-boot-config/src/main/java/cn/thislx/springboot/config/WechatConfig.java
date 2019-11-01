package cn.thislx.springboot.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取wechat配置文件
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/9/18 15:35
 **/
@Component
@ConfigurationProperties("wechat")
//@PropertySource(value = "classpath:wechat.properties")
public class WechatConfig {

    private String appId;

    private String lakala_appId;
}
