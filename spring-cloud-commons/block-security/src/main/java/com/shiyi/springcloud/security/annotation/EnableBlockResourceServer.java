
package com.shiyi.springcloud.security.annotation;

import com.shiyi.springcloud.security.config.ResouceServerConfig;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.*;

/**
 *  资源服务注解
 * @author ：ShiYI
 * @date ：Created in 2021/10/14
 */
@Documented
@Inherited
@EnableResourceServer
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@EnableGlobalMethodSecurity(prePostEnabled = true)
@Import({ ResouceServerConfig.class})
public @interface EnableBlockResourceServer {

}
