package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Answer;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerItemDTOTest {

    @Test
    public void testToDTO() {
        Answer answer = new Answer();
        answer.setId(Long.parseLong("1"));
        answer.setName("Тестовый вопрос");

        AnswerItemDTO answerItemDTO = new AnswerItemDTO(answer,Boolean.FALSE);

        assertEquals("Тестовый вопрос", answerItemDTO.answerText);
        assertEquals(Boolean.FALSE, answerItemDTO.isCorrect);
    }

}