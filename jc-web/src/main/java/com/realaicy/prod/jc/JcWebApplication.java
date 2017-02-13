package com.realaicy.prod.jc;

import com.realaicy.prod.jc.common.properties.StudyProperties;
import com.realaicy.prod.jc.lib.core.data.jpa.SimpleBaseJPARepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Jc web application.
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseJPARepository.class)
@EnableConfigurationProperties({StudyProperties.class})
public class JcWebApplication {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(JcWebApplication.class, args);
    }
}
