package edu.nf.student.controller.config;

import edu.nf.student.controller.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author admin
 * @date 2019/11/18
 * spring mvc 文件
 */
@Configuration
@ComponentScan(basePackages = "edu.nf.student.controller")
@EnableWebMvc
@CrossOrigin(origins = "*", methods = {RequestMethod.GET,RequestMethod.POST})
public class MvcConfig implements WebMvcConfigurer {


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new DateFormatter("yyyy-MM-dd"));
    }

    /**
     * 方式二：使用spring mvc处理静态资源
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/page/**")
            .addResourceLocations("/static/");
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:D:/upload/");
        }

//    /**
//     * 默认处理静态资源
//     * @param configurer
//     */
//    @Override
//    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
//        configurer.enable();
//    }
    @Bean
    public InternalResourceViewResolver viewResolver(){
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }





    /**
     * 配置拦截器
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/page/login.html","/page/fonts/**","/page/assets/**", "/login","/page/images/**", "/page/js/**", "/page/css/**");
    }


    /**
     * 配置commons-upload上传
     */
    @Bean
    public CommonsMultipartResolver multipartResolver(){
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        //设置文件上传的大小
        resolver.setMaxUploadSize(104857600);
        //设置编码
        resolver.setDefaultEncoding("UTF-8");
        return resolver;
    }

}