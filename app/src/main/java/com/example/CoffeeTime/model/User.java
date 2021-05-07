package com.example.CoffeeTime.model;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable {

    private String id;
    private String name;
    private String organizationId; // Max 5

    public String getId() {
        return id;
    }

    public String getOrganizations() {
        return organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOrganizations(String organization) {
        this.organizationId = organization;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id.equals(user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
