package com.realaicy.prod.jc;

import com.realaicy.prod.jc.common.properties.StudyProperties;
import com.realaicy.prod.jc.lib.core.data.jpa.SimpleBaseJPARepository;
import com.realaicy.prod.jc.realglobal.config.StaticParams;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Jc web application.
 */
//@Configuration
@EnableAutoConfiguration
@EnableAspectJAutoProxy
@ComponentScan(basePackages={"com.realaicy.prod.jc"},
        excludeFilters = {@ComponentScan.Filter(value = Controller.class),
                @ComponentScan.Filter(value = RestController.class)})
@EnableJpaRepositories(repositoryBaseClass = SimpleBaseJPARepository.class)
@EnableConfigurationProperties({StudyProperties.class})
@Profile({StaticParams.SPRINGPROFILES.TEST_SERVICE})
public class JcServiceTestContext {

}
