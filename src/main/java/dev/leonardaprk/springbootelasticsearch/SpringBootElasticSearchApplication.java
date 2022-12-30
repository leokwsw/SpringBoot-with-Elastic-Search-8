package dev.leonardaprk.springbootelasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.data.elasticsearch.config.EnableElasticsearchAuditing;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.transaction.annotation.Transactional;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class SpringBootElasticSearchApplication extends SpringBootServletInitializer {

  static int staticServerPort;

  @Value("${server.port}")
  public void setStaticServerPort(int port) {
    SpringBootElasticSearchApplication.staticServerPort = port;
  }

  public static void main(String[] args) {
    SpringApplication.run(SpringBootElasticSearchApplication.class, args);
    System.out.printf("API Doc : %s/swagger-ui/index.html%n", String.format("http://localhost:%s", staticServerPort));
  }


  @Bean
  CommandLineRunner run() {
    return args -> {

    };
  }
}
