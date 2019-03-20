package com.github.akraskovski.pbsf.security.annotations;

import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Secured method access annotation.
 * <p>
 * Defines the availability of the acquiring resource.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Secured {

    /**
     * Allowed scope(flow) being access by a user.
     *
     * @return scope value
     */
    Scope scope();

    /**
     * Accessing entity types.
     * <p>
     * !WARNING!
     * If none is specified the default value will be selected from the scope.getEntities()
     * !WARNING!
     *
     * @return available entity types
     */
    Class<? extends Object>[] entities() default {};

    /**
     * Allowed enums types over the accessing entity.
     *
     * @return the enums
     */
    EntityAction[] actions();
}
