package com.community.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
	
	/**
	 * RequestHandlerSelectors.basePackage("com.example")  配置需要扫描的包
	 * .paths  过滤什么路径，只️扫描包
	 * @return
	 */
	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).forCodeGeneration(true).select()
				.apis(RequestHandlerSelectors.basePackage("com.community"))
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("APIs").version("1.0")
				.contact(new Contact("api", "", "admin@infox.com")).build();
	}

}
