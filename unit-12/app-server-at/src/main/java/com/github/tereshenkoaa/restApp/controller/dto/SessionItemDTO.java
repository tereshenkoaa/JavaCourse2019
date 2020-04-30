package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Session;

public class SessionItemDTO extends JournalItemDTO{

    public String name;
    public Double result;

    public SessionItemDTO() {
    }

    public SessionItemDTO(Session session) {
        this.id = session.getId().toString();
        this.name = session.getName();
        this.result = session.getPercent();
    }
}
