package com.github.akraskovski.pbsf.security.endpoints;

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
     * @param entity the entity
     * @return the object
     */
//todo here should be an access definition
    Object findOwner(Entity entity);
}
