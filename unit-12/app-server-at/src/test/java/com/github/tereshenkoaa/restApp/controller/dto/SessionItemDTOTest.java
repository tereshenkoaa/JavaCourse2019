package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Session;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SessionItemDTOTest {

    @Test
    public void SessionItemDTOTest() {
        Session session = new Session();
        session.setId(Long.parseLong("1"));
        session.setName("Тестовый тестер");
        session.setPercent(Double.parseDouble("99"));

        SessionItemDTO sessionItemDTO = new SessionItemDTO(session);

        assertEquals("Тестовый тестер", sessionItemDTO.name);
        assertEquals("1", sessionItemDTO.id);
        assertEquals(Double.parseDouble("99 "), sessionItemDTO.result);
    }

}