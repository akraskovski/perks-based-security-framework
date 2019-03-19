package com.github.akraskovski.pbsf.security.managers;

import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.context.SecurityContextHolder;
import com.github.akraskovski.pbsf.security.endpoints.SecuredEntityEndpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        // Not really necessary that all of these fields will come useful in future.
        // So here just extracted all available variables which could be used in validation.
        var methodScope = methodAnnotation.scope();
        var methodActions = methodAnnotation.actions();
        var methodAccessLevel = methodAnnotation.accessLevel();
        var currentUser = SecurityContextHolder
            .getContext()
            // todo: change these lines when context holder will be ready
            .orElseGet(User::new);
//            .orElseThrow(() -> new RuntimeException("Unauthorized, 401 should be thrown"));
        var userRole = currentUser.getRole();
        var methodArguments = buildMethodArguments(pjp);
        var endpoint = (SecuredEntityEndpoint<?>) pjp.getTarget();
        var processingEntity = endpoint.getProcessingEntity();
        var entityOwner = Optional.ofNullable(methodArguments.get("id"))
            .map(String::valueOf)
            .map(endpoint::findOwner)
            .orElse(null);

        // todo: not clear we'll validate read/write/operate actions here
        // todo: need to define steps.
        // ?
        userRole.hasAccess();

        // 1.

        // 2.

        // 3.

        return true;
    }

    private Map<String, Object> buildMethodArguments(ProceedingJoinPoint pjp) {
        var argsNames = ((MethodSignature) pjp.getSignature()).getParameterNames();
        var argsValues = pjp.getArgs();

        return IntStream
            .range(0, argsNames.length)
            .boxed()
            .collect(Collectors.toMap(it -> argsNames[it], it -> argsValues[it]));
    }
}
