package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Journal;
import com.github.tereshenkoaa.restApp.service.JournalServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JournalEntityDTOTest {

    @Test
    public void JournalEntityDTOTest() {
        Journal journal = new Journal();
        journal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
        journal.setName("Тестовый журнал");
        journal.setDefaultPageSize((long) 15);

        JournalEntityDTO journalEntityDTO = new JournalEntityDTO(journal);

        assertEquals("Тестовый журнал", journalEntityDTO.name);
        assertEquals("questions", journalEntityDTO.id);
        assertEquals(Long.parseLong("15"), journalEntityDTO.defaultPageSize);
    }

}