package com.example.CoffeeTime.model;

import java.util.Objects;
import java.util.UUID;

public class Organization {

    private String id;
    private String name;
    private int members;

    public Organization(){
        id = UUID.randomUUID().toString();
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMembers(int members) {
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public int getMembers() {
        return members;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization org = (Organization) o;
        return Objects.equals(id, org.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
