package edu.nf.student.controller.config;

import ch.qos.logback.ext.spring.web.LogbackConfigListener;
import edu.nf.student.dao.config.DaoConfig;
import edu.nf.student.service.config.AppConfig;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.EnumSet;

/**
 * @author admin
 * @date 2019/11/18
 * web.xml
 */
public class WebInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    /**
     * 指定AppConfig配置类
     * @return
     */
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{AppConfig.class};
    }

    /**
     * 指定spring mvc的配置类
     * @return
     */
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{MvcConfig.class};
    }

    /**
     * 指定DispatcherServlet的请求映射url
     * @return
     */
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    /**
     * onStarup方法中配置其他的字符过滤器，logback
     * @param servletContext
     * @throws ServletException
     */
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //配置spring-logback
        servletContext.addListener(LogbackConfigListener.class);
        servletContext.setInitParameter("logbackConfigLocation","classpath:logback.xml");

        //注册自定义过滤器
        //注册spring mvc提供的字符编码过滤器
        FilterRegistration.Dynamic encodingFilter = servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilter.setInitParameter("encoding","utf-8");
        encodingFilter.setInitParameter("forceEncoding","true");
        encodingFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), true, "/*");
                super.onStartup(servletContext);
    }
}