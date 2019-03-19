/*
 * This portion of the source code is copyrighted by Thumbtack Technology LLC,
 * all rights reserved, and is subject to the terms of a license agreement
 * in which it constitutes “Pre-existing work” or "Licensed Product".
 */
package com.github.akraskovski.pbsf.security.access;

/**
 * EntityAccessDefinition.
 */
public interface EntityAccessDefinition {

    /**
     * Entity type name.
     */
    String entityName();

    /**
     * Get Owner (Advertiser|Publisher|Account) ID by entity ID.
     */
    String whoOwns(String entityId);
}
