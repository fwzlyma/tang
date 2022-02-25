package com.nw.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : baitao
 * @Time : 2021/8/20 17:11
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //默认地址（可以是页面或后台请求接口）
        registry.addViewController("/").setViewName("forward:index.html");
        //设置过滤优先级最高
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
// 过滤器先注释了
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginHandlerInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/login.html", "/index.html", "/","about.html",
//                        "/user/login","/user/regist","/resource/index","/resource/toAbout","/resource/toMembership","/resource/toVenue",
//                        "/resource/toEvent2","/resource/toEvent1","/resourceClassType/toAllType","/resource/resourceListMain",
//                        "/resource/toDiagram","/resource/toColor","/resourceClassType/getTypeList","/resourceClassType/getDetailType",
//                        "/resource/getDetail","/resource/toType","/resource/allResource","/resource/getResourceListByType","/resource/resourceListMain",
//                        "/css/**","../static/js/**","/fonts/**","../static/images/**","/lib/**","/ueditor/**");
//    }
}
