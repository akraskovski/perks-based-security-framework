package com.github.akraskovski.pbsf.security.annotations;

import com.github.akraskovski.pbsf.security.enums.AccessLevel;
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
     * Entity access level restriction
     *
     * @return the access level
     */
    // todo: is it still be here?
    AccessLevel accessLevel();
}
