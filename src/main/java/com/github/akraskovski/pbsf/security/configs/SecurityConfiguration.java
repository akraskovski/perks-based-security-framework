package com.github.akraskovski.pbsf.security.configs;

import com.github.akraskovski.pbsf.security.aspects.SecuredRequestsAccessAspect;
import com.github.akraskovski.pbsf.security.managers.SecuredAccessManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Basic security module configuration.
 */
@Configuration
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class SecurityConfiguration {

    /**
     * Secured requests access aspect.
     *
     * @return the secured requests access aspect
     */
    @Bean
    public SecuredRequestsAccessAspect securedRequestsAccessAspect(SecuredAccessManager accessManager) {
        return new SecuredRequestsAccessAspect(accessManager);
    }
}
