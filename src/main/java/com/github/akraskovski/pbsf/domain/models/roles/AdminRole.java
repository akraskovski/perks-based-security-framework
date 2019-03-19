package com.github.akraskovski.pbsf.domain.models.roles;

import com.github.akraskovski.pbsf.domain.models.Role;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The type Admin role.
 */
@Component
public class AdminRole extends Role {

    public AdminRole() {
        super(
            Set.of(EntityAction.READ, EntityAction.WRITE, EntityAction.OPERATE),
            Set.of(Scope.CAMPAIGN_MANAGEMENT, Scope.PUBLISHER_MANAGEMENT, Scope.USER_MANAGEMENT)
        );
    }
}
