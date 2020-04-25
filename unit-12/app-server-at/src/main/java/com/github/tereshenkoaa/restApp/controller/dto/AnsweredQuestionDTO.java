package com.github.tereshenkoaa.restApp.controller.dto;

import java.util.List;

public class AnsweredQuestionDTO {

    public String name;
    public List<SessionQuestionListDTO> questionsList;

    public AnsweredQuestionDTO() {
    }

    public AnsweredQuestionDTO(String name, List<SessionQuestionListDTO> questionsList) {
        this.name = name;
        this.questionsList = questionsList;
    }
}
