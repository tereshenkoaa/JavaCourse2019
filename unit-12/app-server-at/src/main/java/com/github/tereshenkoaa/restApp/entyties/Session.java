package com.github.tereshenkoaa.restApp.entyties;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Session extends BaseEntity {

    @Column(name = "fio")
    private String name;
    @Column
    private Double percent;

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
