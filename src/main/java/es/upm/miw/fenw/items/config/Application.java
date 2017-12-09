package es.upm.miw.fenw.items.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import es.upm.miw.fenw.items.daos.DaoFactory;
import es.upm.miw.fenw.items.daos.memory.DaoMemoryFactory;

@SpringBootApplication(scanBasePackages = {Names.ASPECT,Names.RESOURCES, Names.DTOS, Names.CONTROLLERS, Names.DAOS})
@Import(SwaggerConfig.class)
public class Application {
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*").allowedOrigins("*").maxAge(3600);
            }
        };
    }

    @Bean
    public DaoFactory daoFactory() {
        DaoFactory dao =  new DaoMemoryFactory();
        return dao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
