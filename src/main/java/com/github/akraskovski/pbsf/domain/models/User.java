package com.github.akraskovski.pbsf.domain.models;

/**
 * The domain user entity.
 */
public class User {

    private String id;
    private String email;
    private String password;
    private Role role;
    private AccessLevel accessLevel;

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public Role getRole() {
        return role;
    }

    public User setRole(Role role) {
        this.role = role;
        return this;
    }

    public AccessLevel getAccessLevel() {
        return accessLevel;
    }

    public User setAccessLevel(AccessLevel accessLevel) {
        this.accessLevel = accessLevel;
        return this;
    }
}
