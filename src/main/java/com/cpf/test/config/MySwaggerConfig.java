package com.cpf.test.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.dto.ApiInfo;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;

@Component
@EnableSwagger
@EnableWebMvc
public class MySwaggerConfig {

	@Autowired
	private SpringSwaggerConfig springSwaggerConfig;

	@Bean
	public SwaggerSpringMvcPlugin customImplementation() {
		
		String[] patterns = {"/admin.*","/user.*"};
		
		return new SwaggerSpringMvcPlugin(this.springSwaggerConfig).apiInfo(
				apiInfo()).includePatterns(patterns);
	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("My Project's REST API",
				"This is a description of your API.", "API TOS",
				"me@wherever.com", "API License", "API License URL");
		return apiInfo;
	}
}
