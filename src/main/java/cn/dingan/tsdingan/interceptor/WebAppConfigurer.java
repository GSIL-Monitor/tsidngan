package cn.dingan.tsdingan.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 
* @ClassName: 自定义拦截器
* @Description: TODO(这里用一句话描述这个类的作用)
* @author jyq#trasen.cn
* @date 2018年5月3日 下午4:52:58
*
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
	
	@Autowired
	private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**").excludePathPatterns("/index/*");

        super.addInterceptors(registry);
    }
}
