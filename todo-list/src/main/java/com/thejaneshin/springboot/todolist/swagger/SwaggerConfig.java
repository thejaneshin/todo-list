package com.thejaneshin.springboot.todolist.swagger;

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

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.thejaneshin.springboot.todolist.rest"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
				.title("Todo List REST API")
				.description("\"Todo List REST API documentation\"")
				.version("1.0.0")
				.license("Apache 2.0")
				.licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
				.contact(new Contact("Jane Shin", "https://github.com/thejaneshin", "thejaneshin@gmail.com"))
				.build();
	}
}
