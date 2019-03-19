package com.github.akraskovski.pbsf.domain.models;

import com.github.akraskovski.pbsf.security.annotations.Secured;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;

import java.util.Arrays;
import java.util.Set;

/**
 * The domain role entity.
 */
public abstract class Role {

    private final Set<EntityAction> actions;
    private final Set<Scope> scopes;

    protected Role(Set<EntityAction> actions, Set<Scope> scopes) {
        this.actions = actions;
        this.scopes = scopes;
    }

    /**
     * Has access validation contract.
     *
     * @param methodAnnotation the method annotation metadata
     * @return the result of accessing the resource
     */
    public boolean hasAccess(Secured methodAnnotation) {
        return scopes.contains(methodAnnotation.scope()) &&
            actions.containsAll(Arrays.asList(methodAnnotation.actions()));
    }

    public Set<EntityAction> getActions() {
        return actions;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }
}
