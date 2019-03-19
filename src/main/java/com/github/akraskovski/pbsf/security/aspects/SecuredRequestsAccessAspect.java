package com.github.akraskovski.pbsf.security.aspects;

import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.managers.SecuredAccessManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * General application security entry point.
 */
@Aspect
public class SecuredRequestsAccessAspect {

    private final SecuredAccessManager accessManager;

    @Autowired
    public SecuredRequestsAccessAspect(SecuredAccessManager accessManager) {
        this.accessManager = accessManager;
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

        if (accessManager.hasAccess(pjp, secured)) {
            return pjp.proceed();
        }

        throw new RuntimeException("Forbidden, 403");
    }

    private boolean isAlreadyAuthorized() {
        var sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

        // todo context holder checks for current user
        return true;
    }
}
