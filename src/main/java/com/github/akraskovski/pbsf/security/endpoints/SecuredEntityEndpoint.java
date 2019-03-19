package com.github.akraskovski.pbsf.security.endpoints;

import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;

/**
 * Base endpoint securing behavior rules
 *
 * @param <Entity> the type parameter
 */
public interface SecuredEntityEndpoint<Entity> {

    /**
     * Gets processing entity.
     *
     * @return the processing entity
     */
    Class<Entity> getProcessingEntity();

    /**
     * Gets entity access definition.
     *
     * @return the entity access definition
     */
    EntityAccessDefinition getEntityAccessDefinition();

    /**
     * Find owner object.
     *
     * @param id the id
     * @return the object
     */
    default String findOwner(String id) {
        return getEntityAccessDefinition().whoOwns(id);
    }
}
