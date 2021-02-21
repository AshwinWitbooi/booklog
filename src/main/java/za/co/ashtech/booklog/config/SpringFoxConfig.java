package za.co.ashtech.booklog.config;

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
public class SpringFoxConfig {                                    
    @Bean
    public Docket api() { 
        return new Docket(DocumentationType.SWAGGER_2)
		  .apiInfo(apiInfo())
          .select()                                 
          .apis(RequestHandlerSelectors.basePackage("za.co.ashtech.booklog.controller"))              
          .paths(PathSelectors.any())                          
          .build();                                           
    }
    
    ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("Booklog API")
				.description("Booklog API for developers that want to manage books")
				.contact(new Contact("admin@blog.co.za", "http://www.booklog.co.za", "administrator")).license("Booklog License")
				.licenseUrl("admin@blog.co.za").version("1.0").build();
	}
}
