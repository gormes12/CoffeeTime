package com.example.CoffeeTime.model;

import java.util.Objects;
import java.util.UUID;

public class Organization {

    public String UniqueID;
    public String Name;
    public int NumOfMembers;

    public Organization(){
        UniqueID = UUID.randomUUID().toString();
    }

    public void setUniqueID(String uniqueID) {
        UniqueID = uniqueID;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setNumOfMembers(int numOfMembers) {
        NumOfMembers = numOfMembers;
    }

    public String getUniqueID() {
        return UniqueID;
    }

    public int getNumOfMembers() {
        return NumOfMembers;
    }

    public String getName() {
        return Name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Organization org = (Organization) o;
        return Objects.equals(UniqueID, org.UniqueID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(UniqueID);
    }
}
