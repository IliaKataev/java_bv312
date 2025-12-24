package com.example;

//import com.example.domain.LegoXMLJavaConfig;
import com.example.domain.CityProfileConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

@SpringBootApplication
public class FirstSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringApplication.class, args);

//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();//LegoXMLJavaConfig.class);
//        context.getEnvironment().setActiveProfiles("dev");
//        context.register(CityProfileConfig.class);
//        context.refresh();
    }

}
