package cn.thislx.security.config;

import cn.thislx.security.filter.VerifyFilter;
import cn.thislx.security.handle.CustomAuthenticationFailureHandler;
import cn.thislx.security.handle.CustomAuthenticationSuccessHandler;
import cn.thislx.security.handle.CustomLogoutSuccessHandler;
import cn.thislx.security.permission.CustomPermissionEvaluator;
import cn.thislx.security.provider.CustomAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationDetailsSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

/**
 * security配置类
 * <p>
 * 该类是 Spring Security 的配置类，该类的三个注解分别是标识该类是配置类、开启 Security 服务、开启全局 Securtiy 注解。
 * 首先将我们自定义的 userDetailsService 注入进来，
 * 在 configure() 方法中使用 auth.userDetailsService() 方法替换掉默认的 userDetailsService。
 * 这里我们还指定了密码的加密方式（5.0 版本强制要求设置），因为我们数据库是明文存储的，所以明文返回即可，如下所示：
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/10/20 17:41
 **/
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AuthenticationDetailsSource<HttpServletRequest, WebAuthenticationDetails> authenticationDetailsSource;

    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler customLogoutSuccessHandler;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(customAuthenticationProvider);
    }

    /**
     * 注入自定义PermissionEvaluator
     */
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler() {
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new CustomPermissionEvaluator());
        return handler;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 关闭CSRF跨域
        http.csrf().disable();

        // 设置验证码过滤器  作用于CustomAuthenticationProvider 保持一致  使用一个即可
        http.addFilterBefore(new VerifyFilter(), UsernamePasswordAuthenticationFilter.class);
        http.formLogin()
                // 设置登陆的URL
                .loginProcessingUrl("/login")
                .successHandler(customAuthenticationSuccessHandler)
                .failureHandler(customAuthenticationFailureHandler)
                .authenticationDetailsSource(authenticationDetailsSource);

        // 退出操作配置
        http.logout()
                // 清除SecurityContextHandler中的授权信息
                .clearAuthentication(false)
                // 退出的URL
                .logoutUrl("/logout")
                // 清除Cookies
                .deleteCookies("JSESSIONID")
                // 退出成功执行的Handler
                .logoutSuccessHandler(customLogoutSuccessHandler);

        // 配置请求授权
        http.authorizeRequests()
                // 过滤URL地址
                .antMatchers("/getVerifyCode", "/login/invalid")
                .permitAll()
                .anyRequest()
                .authenticated();

        // 配置记住密码
        http.rememberMe().tokenRepository(persistentTokenRepository()).tokenValiditySeconds(60);

        // 配置Session
        http.sessionManagement().invalidSessionUrl("/login/invalid").maximumSessions(1).maxSessionsPreventsLogin(false);

    }


    /**
     * 过滤静态资源，
     * 顾名思义，WebSecurity主要是配置跟web资源相关的，比如css、js、images等等，但是这个还不是本质的区别，关键的区别如下：
     * ingore是完全绕过了spring security的所有filter，相当于不走spring security
     * permitall没有绕过spring security，其中包含了登录的以及匿名的。
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        // 设置拦截忽略文件夹，可以对静态资源放行
        web.ignoring().antMatchers("/css/**", "/js/**");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 如果token表不存在，使用下面语句可以初始化该表；若存在，请注释掉这条语句，否则会报错。
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

}

