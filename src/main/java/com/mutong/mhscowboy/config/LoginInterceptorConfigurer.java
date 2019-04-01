package com.mutong.mhscowboy.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.mutong.mhscowboy.interceptor.LoginInterceptor;


@Configuration
public class LoginInterceptorConfigurer
	implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截路径：必须登录才可以访问
		List<String> patterns = new ArrayList<>();
		patterns.add("/**");
		
		// 白名单：在黑名单范围内，却不需要登录就可以访问
		List<String> excludePatterns = new ArrayList<>();
		excludePatterns.add("/cowboy/css/**");
		excludePatterns.add("/cowboy/img/**");
		excludePatterns.add("/cowboy/js/**");
		excludePatterns.add("/css/**");
		excludePatterns.add("/js/**");
		
		excludePatterns.add("/cowboy/login.html");
		excludePatterns.add("/user/login");
		excludePatterns.add("/cowboy/reg.html");
		excludePatterns.add("/user/reg");
		
		// 注册拦截器
		registry
			.addInterceptor(new LoginInterceptor())
			.addPathPatterns(patterns)
			.excludePathPatterns(excludePatterns);
	}
	
}





