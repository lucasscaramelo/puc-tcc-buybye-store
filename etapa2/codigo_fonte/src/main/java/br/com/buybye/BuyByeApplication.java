package br.com.buybye;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.buybye.database.repository")
public class BuyByeApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuyByeApplication.class, args);
    }
}
