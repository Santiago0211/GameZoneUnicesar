package com.gamezone.model;

import java.io.Serializable;


public abstract class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String name;
    private String phone;

    
    public Person(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

   
    public abstract String getRoleDescription();

    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Phone: " + phone;
    }
}
