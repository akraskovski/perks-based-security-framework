package com.github.akraskovski.pbsf.domain.models.roles;

import com.github.akraskovski.pbsf.domain.models.Role;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * The type Operator role.
 */
@Component
public class OperatorRole extends Role {

    public OperatorRole() {
        super(
            Set.of(EntityAction.READ, EntityAction.WRITE),
            Set.of(Scope.CAMPAIGN_MANAGEMENT, Scope.PUBLISHER_MANAGEMENT)
        );
    }
}
