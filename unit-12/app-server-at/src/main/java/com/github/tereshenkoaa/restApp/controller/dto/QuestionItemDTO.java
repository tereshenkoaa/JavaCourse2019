package com.github.tereshenkoaa.restApp.controller.dto;

import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Question;

import java.util.List;
import java.util.stream.Collectors;

public class QuestionItemDTO extends JournalItemDTO {

    public String name;
    public List<AnswerItemDTO> answers;

    public QuestionItemDTO() {

    }

    public QuestionItemDTO(Question question, List<Answer> answers) {
        this.id = question.getId().toString();
        this.name = question.getName();
        this.answers = answers.stream()
                .map(AnswerItemDTO::new)
                .collect(Collectors.toList());
    }

    public QuestionItemDTO(Question question, List<Answer> answers, Boolean correct) {
        this.id = question.getId().toString();
        this.name = question.getName();
        this.answers = answers.stream()
                .map(answer -> new AnswerItemDTO(answer, correct))
                .collect(Collectors.toList());
    }

}
