package com.github.akraskovski.pbsf.domain.models;

import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;

/**
 * The type Access level.
 */
public interface AccessLevel {

    /**
     * Restricts access to the resource according to the current user's access level.
     *
     * @param accessDefinition the access definition
     * @param entityId         the entity id
     * @param accessingUser    the accessing user
     * @return boolean
     */
    boolean accept(EntityAccessDefinition accessDefinition, String entityId, User accessingUser);
}
