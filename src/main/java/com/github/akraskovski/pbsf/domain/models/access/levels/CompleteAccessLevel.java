package com.github.akraskovski.pbsf.domain.models.access.levels;

import com.github.akraskovski.pbsf.domain.models.AccessLevel;
import com.github.akraskovski.pbsf.domain.models.User;
import com.github.akraskovski.pbsf.security.access.EntityAccessDefinition;
import org.springframework.stereotype.Component;

/**
 * The type Complete access level.
 */
@Component
public class CompleteAccessLevel implements AccessLevel {

    @Override
    public boolean accept(EntityAccessDefinition accessDefinition, String entityId, User accessingUser) {
        return true;
    }
}
