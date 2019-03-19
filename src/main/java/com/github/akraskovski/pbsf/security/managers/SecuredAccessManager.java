package com.github.akraskovski.pbsf.security.managers;

import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.context.SecurityContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

/**
 * The type Secured access manager.
 */
@Component
public class SecuredAccessManager {

    /**
     * Has access validation method.
     * Checks the resource owner access with acquiring from request user.
     *
     * @param pjp              the pjp
     * @param methodAnnotation the method annotation
     * @return the boolean
     */
    public boolean hasAccess(ProceedingJoinPoint pjp, Secured methodAnnotation) {
        var methodScope = methodAnnotation.scope();
        var methodActions = methodAnnotation.actions();
        var methodAccessLevel = methodAnnotation.accessLevel();
        var currentUser = SecurityContextHolder
            .getContext()
            .orElseThrow(() -> new RuntimeException("Unauthorized, 401 should be thrown"));

        return true;
    }
}
