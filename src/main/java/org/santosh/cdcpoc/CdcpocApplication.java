package org.santosh.cdcpoc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "org.santosh.cdcpoc.repo.redis")
@EnableJpaRepositories(basePackages = "org.santosh.cdcpoc.repo.mysql")
public class CdcpocApplication {

    public static void main(String[] args) {
        SpringApplication.run(CdcpocApplication.class, args);
    }

}
