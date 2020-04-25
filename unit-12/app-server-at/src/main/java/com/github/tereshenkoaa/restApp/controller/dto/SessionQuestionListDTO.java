package com.github.tereshenkoaa.restApp.controller.dto;

import java.util.List;

public class SessionQuestionListDTO {

    public String id;
    public List<SessionQuestionAnswerDTO> answersList;

    public SessionQuestionListDTO() {
    }

    public SessionQuestionListDTO(String id, List<SessionQuestionAnswerDTO> answersList) {
        this.id = id;
        this.answersList = answersList;
    }


}
