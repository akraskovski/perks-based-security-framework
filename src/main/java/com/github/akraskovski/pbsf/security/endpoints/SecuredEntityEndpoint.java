package com.github.akraskovski.pbsf.security.endpoints;

import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;

/**
 * Base security rules endpoint.
 */
public interface SecuredEntityEndpoint {

    /**
     * Gets entity access definition.
     *
     * @return the entity access definition
     */
    EntityAccessDefinition getEntityAccessDefinition();
}
