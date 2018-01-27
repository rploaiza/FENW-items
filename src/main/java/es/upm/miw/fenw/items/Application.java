package es.upm.miw.fenw.items;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import es.upm.miw.fenw.items.daos.DaoFactory;
import es.upm.miw.fenw.items.daos.memory.DaoMemoryFactory;

@SpringBootApplication
public class Application {

    @Bean
    public DaoFactory daoFactory() {
        DaoFactory dao = new DaoMemoryFactory();
        return dao;
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
