package com.github.akraskovski.pbsf.security.aspects;

import com.github.akraskovski.pbsf.domain.repositories.UserRepository;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.context.SecurityContextHolder;
import com.github.akraskovski.pbsf.security.managers.SecuredAccessManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * General application security entry point.
 */
@Aspect
public class SecuredRequestsAccessAspect {

    private final SecuredAccessManager accessManager;
    private final UserRepository userRepository;

    @Autowired
    public SecuredRequestsAccessAspect(SecuredAccessManager accessManager, UserRepository userRepository) {
        this.accessManager = accessManager;
        this.userRepository = userRepository;
    }

    @Pointcut("target(com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint)")
    private void securedEndpoint() {
    }

    @Pointcut("@annotation(annotation)")
    private void securedMethod(Secured annotation) {
    }

    @Around(value = "securedMethod(secured) && securedEndpoint()", argNames = "pjp,secured")
    private Object processRequest(final ProceedingJoinPoint pjp, Secured secured) throws Throwable {
        if (!isAlreadyAuthorized()) {
            throw new RuntimeException("Unauthorized, 401 should be thrown");
        }

        if (!accessManager.hasAccess(pjp, secured)) {
            throw new RuntimeException("Forbidden, 403");
        }

        return pjp.proceed();
    }

    private boolean isAlreadyAuthorized() {
        // imagine that we have working authentication mechanism which accepts user request.
        SecurityContextHolder.setContext(userRepository.findById("operator").get());

        return SecurityContextHolder.getContext().isPresent();
    }
}
