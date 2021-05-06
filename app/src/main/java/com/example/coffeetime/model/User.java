package com.example.coffeetime.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {

    public String UniqueID;
    public String NickName;
    public List<Organization> organizations; // Max 5

    public String getUniqueID() {
        return UniqueID;
    }

    public List<Organization> getOrganizations() {
        if (organizations == null){
            organizations = new LinkedList<>();
        }
        return organizations;
    }

    public String getNickName() {
        return NickName;
    }

    public void setNickName(String nickName) {
        NickName = nickName;
    }

    public void setOrganizations(List<Organization> organizations) {
        this.organizations = organizations;
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
