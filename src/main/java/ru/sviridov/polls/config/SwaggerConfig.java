package ru.sviridov.polls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig extends WebMvcConfigurerAdapter {

  /**
   * Настройки swagger-ui
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
    registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
    super.addResourceHandlers(registry);
  }

  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .tags(new Tag("polls", "API по работе с опросами"))
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("ru.sviridov.polls"))
            .paths(PathSelectors.any())
            .build();
  }

  /**
   * Информация о API
   */
  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
            .title("Опросы")
            .description("Опросы")
            .contact(new Contact("Никита Свиридов", "https://t.me/nasviridov", "n.sviridov14@gmail.com"))
            .version("1.0.0")
            .build();
  }

}