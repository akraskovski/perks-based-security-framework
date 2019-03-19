package com.github.akraskovski.pbsf.security.annotations;

import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Secured method/classes access annotation.
 * <p>
 * Define the availability of the acquiring resource.
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
     * Allowed enums types over the accessing entity.
     *
     * @return the enums
     */
    EntityAction[] actions();

    /**
     * Says whether to throw an exception on authentication failure or just skip this request and return a stub.
     *
     * @return needles of throwing an exception
     * @implNote temporary annotation value, should be implemented only for easiest testing...
     */
    boolean faultTolerant() default false;
}
