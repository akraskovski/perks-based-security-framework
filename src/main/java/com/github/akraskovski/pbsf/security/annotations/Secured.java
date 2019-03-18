package com.github.akraskovski.pbsf.security.annotations;

import com.github.akraskovski.pbsf.security.actions.EntityAction;

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
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface Secured {

    /**
     * Allowed scope being access by a user.
     *
     * @return scope value
     */
    Scope scope();

    /**
     * Allowed actions types over the accessing entity.
     *
     * @return the actions
     */
    Class<? extends EntityAction>[] actions();

    /**
     * The enum Scope.
     */
    enum Scope {
        /**
         * Campaign management scope.
         */
        CAMPAIGN_MANAGEMENT,
        /**
         * Project management scope.
         */
        PROJECT_MANAGEMENT,
        /**
         * Something else scope.
         */
        SOMETHING_ELSE
    }
}
