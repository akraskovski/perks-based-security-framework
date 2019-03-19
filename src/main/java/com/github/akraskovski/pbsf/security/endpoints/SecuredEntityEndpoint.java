package com.github.akraskovski.pbsf.security.endpoints;

import com.github.akraskovski.pbsf.domain.models.User;

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
     * Find owner object.
     *
     * @param id the id
     * @return the object
     */
    User findOwner(String id);
}
