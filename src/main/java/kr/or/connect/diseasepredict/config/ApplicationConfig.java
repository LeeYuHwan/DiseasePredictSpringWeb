package kr.or.connect.diseasepredict.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {"kr.or.connect.diseasepredict.dao", "kr.or.connect.diseasepredict.service"})
@Import({DBConfig.class})
public class ApplicationConfig {

}
