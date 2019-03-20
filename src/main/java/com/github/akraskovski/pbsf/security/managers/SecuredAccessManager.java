package com.github.akraskovski.pbsf.security.managers;

import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.context.SecurityContextHolder;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static java.util.Optional.ofNullable;

/**
 * The type Secured access manager.
 */
@Component
public class SecuredAccessManager {

    /**
     * Has access validation method.
     * Checks the resource owner access with acquiring from request user.
     *
     * @param proceedingJoinPoint the proceedingJoinPoint
     * @param methodAnnotation    the method annotation
     * @return the verification result
     */
    public boolean hasAccess(ProceedingJoinPoint proceedingJoinPoint, Secured methodAnnotation) {
        var currentUser = SecurityContextHolder
            .getContext()
            .orElseThrow(() -> new RuntimeException("Unauthorized, 401 should be thrown"));
        var endpoint = (SecuredEntityEndpoint) proceedingJoinPoint.getTarget();
        var entityAccessDefinition = endpoint.getEntityAccessDefinition();
        var entityClass = endpoint.getEntityClass();

        return approvedByAccessLevel(proceedingJoinPoint, currentUser, entityAccessDefinition) &&
            approvedByScopeAndActions(methodAnnotation, currentUser, entityClass);
    }

    private boolean approvedByAccessLevel(ProceedingJoinPoint proceedingJoinPoint,
                                          User currentUser,
                                          EntityAccessDefinition entityAccessDefinition) {
        return ofNullable(buildMethodArguments(proceedingJoinPoint).get("id"))
            .map(String::valueOf)
            .map(id -> currentUser.getAccessLevel().accept(entityAccessDefinition, id, currentUser))
            .orElse(true);
    }

    private boolean approvedByScopeAndActions(Secured methodAnnotation,
                                              User currentUser,
                                              Class<?> accessingEntityClass) {
        return currentUser.getRole().hasAccess(methodAnnotation, accessingEntityClass);
    }

    private Map<String, Object> buildMethodArguments(ProceedingJoinPoint pjp) {
        var argsNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
        var argsValues = pjp.getArgs();

        return IntStream
            .range(0, argsNames.length)
            .boxed()
            .filter(it -> argsValues[it] != null)
            .collect(Collectors.toMap(it -> argsNames[it], it -> argsValues[it]));
    }
}
