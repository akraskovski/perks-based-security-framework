package com.github.akraskovski.pbsf.domain.models.roles;

import com.github.akraskovski.pbsf.domain.models.Role;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The type Supervisor role.
 */
@Component
public class SupervisorRole extends Role {

    public SupervisorRole() {
        super(
            Set.of(EntityAction.READ),
            Set.of(Scope.CAMPAIGN_MANAGEMENT, Scope.PUBLISHER_MANAGEMENT, Scope.USER_MANAGEMENT)
        );
    }
}
