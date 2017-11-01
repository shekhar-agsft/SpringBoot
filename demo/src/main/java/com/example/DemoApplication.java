package com.example;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.utility.HttpStatusCodes;

import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class DemoApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(DemoApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}
	@Bean
	public PasswordEncoder passwordEncoder(){
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	/*
	 * @Bean public Docket swaggerSettings() {
	 * 
	 * Parameter parameter = new
	 * ParameterBuilder().name("Authorization").description("Authorization Token")
	 * .modelRef(new
	 * ModelRef("string")).parameterType("header").required(false).build();
	 * List<Parameter> parameters = new ArrayList<Parameter>();
	 * parameters.add(parameter);
	 * 
	 * Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
	 * .apiInfo(apiInfo()).pathMapping("/").useDefaultResponseMessages(false);
	 * 
	 * docket.globalResponseMessage(RequestMethod.POST, getResponseCodes())
	 * .globalResponseMessage(RequestMethod.PUT, getResponseCodes())
	 * .globalResponseMessage(RequestMethod.GET, getResponseCodes())
	 * .globalResponseMessage(RequestMethod.DELETE, getResponseCodes());
	 * 
	 * return docket; }
	 */
	/*
	 * @Bean public Docket api() { return new
	 * Docket(DocumentationType.SWAGGER_2).select()
	 * .apis(RequestHandlerSelectors.basePackage("com.example.controller")).paths(
	 * PathSelectors.any()).build()
	 * .apiInfo(apiInfo()).pathMapping("/").useDefaultResponseMessages(false);
	 * 
	 * }
	 */
	@Bean
	public Docket swaggerSettings() {
		Parameter parameter = new ParameterBuilder().name("Authorization").description("Authorization Token")
				.modelRef(new ModelRef("string")).parameterType("header").required(false).build();
		List<Parameter> parameters = new ArrayList<Parameter>();
		parameters.add(parameter);
		Docket docket = new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("com.example.controller")).paths(PathSelectors.any()).build()
				.apiInfo(apiInfo()).pathMapping("/").globalOperationParameters(parameters)
				.useDefaultResponseMessages(true);

		/*docket.globalResponseMessage(RequestMethod.POST, getResponseCodes())
				.globalResponseMessage(RequestMethod.PUT, getResponseCodes())
				.globalResponseMessage(RequestMethod.GET, getResponseCodes())
				.globalResponseMessage(RequestMethod.DELETE, getResponseCodes());*/

		return docket;

	}

	private ApiInfo apiInfo() {
		ApiInfo apiInfo = new ApiInfo("Demo for Shekhar REST API", "Rest API Document for App", "API TOS",
				"Terms of service", "Agile Soft Systems India Pvt Ltd", "License of API", "");
		return apiInfo;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ArrayList<ResponseMessage> getResponseCodes() {

		ArrayList responseCodes = new ArrayList();
		responseCodes.add(new ResponseMessageBuilder().code(HttpStatusCodes.OK.value()));
		responseCodes.add(new ResponseMessageBuilder().code(HttpStatusCodes.ALREADY_REGISTERED.value()));
		responseCodes.add(new ResponseMessageBuilder().code(HttpStatusCodes.VALIDATION_ERROR.value()));

		return responseCodes;
	}

	/*
	 * @Bean public Validator jsr303Validator() { return new
	 * LocalValidatorFactoryBean(); }
	 */

	/*@Bean(name = "messageSource")
	public MessageSource getMessageSourceDev() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasenames("messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}*/

}
