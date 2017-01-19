package com.ray.demo.admin.webapp.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

@Configurable
@EnableWebMvc
@EnableAspectJAutoProxy(proxyTargetClass = true)
@ComponentScan(basePackages = {"com.ray.demo.admin"})
public class WebMvcConfiguration extends WebMvcConfigurerAdapter{

    @Bean
    public TemplateResolver defaultTemplateResolver() {
    	ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("/views/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setTemplateMode("LEGACYHTML5");
        //templateResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(defaultTemplateResolver());
        //templateEngine.addDialect(new LayoutDialect());
        templateEngine.addDialect(new ShiroDialect());
        return templateEngine;
    }
	
//    @Bean
//    public ViewResolver tileViewResolver() {
//        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
//        viewResolver.setTemplateEngine(templateEngine());
//        viewResolver.setCharacterEncoding("UTF-8");
//        viewResolver.setContentType("text/html;charset=UTF-8");
//        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE);
//        viewResolver.setCache(false);
//        viewResolver.setViewClass(ThymeleafView.class);
//        return viewResolver;
//    }
    
	@Bean
	public ViewResolver viewResolver(SpringTemplateEngine templateEngine) {

		ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
		viewResolver.setTemplateEngine(templateEngine);
		viewResolver.setCharacterEncoding("UTF-8");
		// viewResolver.setOrder(1);
		viewResolver.setViewNames(new String[] { "*" });
		viewResolver.setCache(false);
		return viewResolver;
	}

}
