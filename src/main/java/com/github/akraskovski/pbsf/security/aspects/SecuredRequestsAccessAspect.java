package com.github.akraskovski.pbsf.security.aspects;

import com.github.akraskovski.pbsf.security.annotations.Secured;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.web.context.request.RequestContextHolder;

import java.util.Arrays;

/**
 * General application security entry point.
 */
@Aspect
public class SecuredRequestsAccessAspect {

    @Pointcut("target(com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint)")
    private void securedEndpoint() {
    }

    @Pointcut("@annotation(annotation)")
    private void securedMethod(Secured annotation) {
    }

    @Around(value = "securedMethod(secured) && securedEndpoint()", argNames = "pjp,secured")
    private Object processRequest(final ProceedingJoinPoint pjp, Secured secured) throws Throwable {
        System.out.println("Scope:" + secured.scope());
        System.out.println("Actions:" + Arrays.toString(secured.actions()));

        if (isAlreadyAuthorized()) {
            return pjp.proceed();
        }

        return pjp.proceed();
    }

    private boolean isAlreadyAuthorized() {
        var sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();

        // todo context holder checks for current user
        return false;
    }
}
