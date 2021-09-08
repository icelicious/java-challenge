package jp.co.axa.apidemo.swagger;

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
/**
 * @description: Swagger2Config
 * @author: Li
 * @version: v1.0
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo()).enable(true)
				.select()
				//only scan controllers
				.apis(RequestHandlerSelectors.basePackage("jp.co.axa.apidemo.controllers"))
				//build
				.paths(PathSelectors.any()).build();
	}
	private ApiInfo apiInfo(){
		//customized swagger ui
        return new ApiInfoBuilder()
                .title("java code challenge")
                .description("java code challenge swagger")
                .contact(new Contact("Li xuefeng", "test", "test@gmail.com"))
                .version("1.0")
                .build();
    }
}
