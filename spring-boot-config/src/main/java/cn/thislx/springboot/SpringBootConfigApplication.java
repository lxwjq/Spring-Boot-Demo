package cn.thislx.springboot;

import cn.thislx.springboot.config.LakalaConfig;
import cn.thislx.springboot.config.ValueConfig;
import cn.thislx.springboot.config.WechatConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringBootConfigApplication {

    @Autowired
    private WechatConfig wechatConfig;


    @Autowired
    private ValueConfig valueConfig;

    @Autowired
    private Environment environment;

    @Autowired
    private LakalaConfig lakalaConfig;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootConfigApplication.class, args);
    }


    @GetMapping("wechat")
    public String readWechatConfig(){
        String property = environment.getProperty("wechat.wechat_appId");
        System.out.println(property);
        System.out.println(lakalaConfig);
        System.out.println(valueConfig);
        return wechatConfig.toString();
    }

}
