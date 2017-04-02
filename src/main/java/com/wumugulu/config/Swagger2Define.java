package com.wumugulu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Define {

	@Bean
    public Docket userApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(userApiInfo())
        		.groupName("full-API")
                .genericModelSubstitutes(DeferredResult.class)
                // .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .forCodeGeneration(false)
                // base，最终调用接口后会和paths拼接在一起
                .pathMapping("/")
                .select()
                // 过滤的接口
                .paths(getPaths())
                // .paths( PathSelectors.regex("/users.*"))
                .build();
    }
	
	private Predicate<String> getPaths(){
		return Predicates.or(	PathSelectors.regex("/users.*"),
								PathSelectors.regex("/books.*") ); 
	}
 
	
	private ApiInfo userApiInfo() {
        return new ApiInfoBuilder()
        		// 大标题
        		.title("享生活- API接口列表")
        		// 详细描述
        		.description("小标题-绝对好使")
        		// 版本
        		.version("1.0.0")
        		// 链接地址
        		// .termsOfServiceUrl("http://www.sina.com")
        		// 作者
        		// .contact(new Contact("乌木轱辘", "https://www.toutiao.com/c/user/52535034867/#mid=1559835256691714", "20668627@qq.com"))
        		// license文字
        		// .license("The Apache License, Version 2.0")
        		// license链接
        		// .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
        		.build();
	}
	
}
