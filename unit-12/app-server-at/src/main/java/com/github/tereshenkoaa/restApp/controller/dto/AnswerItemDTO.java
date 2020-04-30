package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Answer;

public class AnswerItemDTO extends JournalItemDTO{

    public String answerText;
    public Boolean isCorrect;

    public AnswerItemDTO() {

    }

    public AnswerItemDTO(Answer answer) {
        id = answer.getId().toString();
        answerText = answer.getName();
        isCorrect = answer.getCorrect();
    }

    public AnswerItemDTO(Answer answer, Boolean correct) {
        id = answer.getId().toString();
        answerText = answer.getName();
        isCorrect = correct;
    }

}
