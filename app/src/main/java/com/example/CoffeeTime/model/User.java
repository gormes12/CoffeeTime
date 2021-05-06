package com.example.CoffeeTime.model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

    public String UniqueID;
    public String NickName;
    public Organization organization; // Max 5

    public String getUniqueID() {
        return UniqueID;
    }

    public Organization getOrganizations() {
        return organization;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setOrganizations(Organization organization) {
        this.organization = organization;
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return UniqueID.equals(user.UniqueID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UniqueID, NickName);
    }
}
