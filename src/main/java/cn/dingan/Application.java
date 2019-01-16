package cn.dingan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

 
@SpringBootApplication
@tk.mybatis.spring.annotation.MapperScan(basePackages = "cn.dingan.*.dao")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        // resolver.setDefaultEncoding("UTF-8");
        // resolver.setResolveLazily(true);//
        // resolveLazily属性启用是为了推迟文件解析，以在在UploadAction中捕获文件大小异常
        // resolver.setMaxInMemorySize(40960);
        resolver.setMaxUploadSize(500 * 1024 * 1024);// 上传文件大小 5M 5*1024*1024
        return resolver;
    }
}
