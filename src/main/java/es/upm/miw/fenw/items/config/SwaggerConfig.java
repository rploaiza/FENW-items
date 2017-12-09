package es.upm.miw.fenw.items.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
 * CLASE DE  CONFIGURACION PARA  EL SWAGGER
 * 
 * URL DEL SWAGGER:  http://localhost:8080/SPRING.tpv.1.2.0-SNAPSHOT/api/swagger-ui.html
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*
     * Metodo para configurar el swagger donde se define: que tipo de peticiones, que clases de parametros admite, que informacion generica
     * se quiere mostrar, etc
     */
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any()).paths(PathSelectors.any()).build()
                .apiInfo(apiInfo());
    }

    /*
     * Metodo en el cual se detalla la informacion generia que se quiere mostrar del swagger
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Máster en Ingeniería Web (UPM). FENW. Items")
                .description("Swagger de Items. https://github.com/miw-upm/FENW-items")
                .version("1.1.0").build();
    }

}
