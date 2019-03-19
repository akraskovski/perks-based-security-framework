package com.github.akraskovski.pbsf.domain.models;

import com.github.akraskovski.pbsf.security.enums.AccessLevel;
import com.github.akraskovski.pbsf.security.enums.EntityAction;
import com.github.akraskovski.pbsf.security.enums.Scope;

import java.util.Set;

/**
 * The domain role entity.
 */
public class Role {

    private Set<EntityAction> actions;
    private Set<Scope> scopes;
    private AccessLevel accessLevel;

    public Set<EntityAction> getActions() {
        return actions;
    }

    public Role setActions(Set<EntityAction> actions) {
        this.actions = actions;
        return this;
    }

    public Set<Scope> getScopes() {
        return scopes;
    }

    public Role setScopes(Set<Scope> scopes) {
        this.scopes = scopes;
        return this;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public Role setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }
}
