package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Journal;

public class JournalEntityDTO {

    public String id;
    public String name;
    public Long defaultPageSize;

    public JournalEntityDTO(Journal journal) {
        this.id = journal.getId();
        this.name = journal.getName();
        this.defaultPageSize = journal.getDefaultPageSize();
    }

}
