package com.github.akraskovski.pbsf.security.enums;

import com.github.akraskovski.pbsf.domain.models.AdGroup;
import com.github.akraskovski.pbsf.domain.models.Advertiser;
import com.github.akraskovski.pbsf.domain.models.Campaign;
import com.github.akraskovski.pbsf.domain.models.Creative;
import com.github.akraskovski.pbsf.domain.models.Publisher;
import com.github.akraskovski.pbsf.domain.models.User;

import java.util.List;

/**
 * The enum Scope.
 */
public enum Scope {

    /**
     * Campaign management scope.
     */
    CAMPAIGN_MANAGEMENT(
        List.of(
            Campaign.class,
            AdGroup.class,
            Creative.class,
            Advertiser.class
        )
    ),

    /**
     * Publisher management scope.
     */
    PUBLISHER_MANAGEMENT(
        List.of(
            Publisher.class
        )
    ),

    /**
     * User management scope.
     */
    USER_MANAGEMENT(
        List.of(
            User.class
        )
    );

    private final List<Class<? extends Object>> entityTypes;

    Scope(final List<Class<? extends Object>> entityTypes) {
        this.entityTypes = entityTypes;
    }

    /**
     * Gets entity types.
     *
     * @return the entity types
     */
    public List<Class<? extends Object>> getEntityTypes() {
        return entityTypes;
    }

}