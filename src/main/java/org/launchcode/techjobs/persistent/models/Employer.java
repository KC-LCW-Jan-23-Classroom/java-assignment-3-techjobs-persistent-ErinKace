package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;

@Entity
public class Employer extends AbstractEntity {
    private String location;
    public Employer() {

    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
