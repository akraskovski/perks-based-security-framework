package com.github.akraskovski.pbsf.domain.models;

/**
 * The type Campaign.
 */
public class Campaign {

    private String id;
    private String name;
    private User owner;

    public String getId() {
        return id;
    }

    public Campaign setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Campaign setName(String name) {
        this.name = name;
        return this;
    }

    public User getOwner() {
        return owner;
    }

    public Campaign setOwner(User owner) {
        this.owner = owner;
        return this;
    }
}
