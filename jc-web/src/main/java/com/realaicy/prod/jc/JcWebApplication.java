package com.realaicy.prod.jc;

import com.realaicy.prod.jc.common.RealBaseMapper;
import com.realaicy.prod.jc.common.properties.StudyProperties;
import com.realaicy.prod.jc.lib.core.data.jpa.SimpleBaseJPARepository;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * The type Jc web application.
 */
@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseJPARepository.class)
@MapperScan(basePackages = "com.realaicy.prod.jc.modules.demo.respos.mybatis", markerInterface = RealBaseMapper.class)

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
