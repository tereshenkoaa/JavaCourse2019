package com.github.tereshenkoaa.restApp.entyties;

import javax.persistence.*;

@Entity
public class Question extends BaseEntity{

    @Column
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
