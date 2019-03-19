package com.github.akraskovski.pbsf.domain.models.roles;

import com.github.akraskovski.pbsf.domain.models.Role;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The type Executor role.
 */
@Component
public class ExecutorRole extends Role {

    public ExecutorRole() {
        super(
            Set.of(EntityAction.READ, EntityAction.OPERATE),
            Set.of(Scope.CAMPAIGN_MANAGEMENT, Scope.PUBLISHER_MANAGEMENT)
        );
    }
}
