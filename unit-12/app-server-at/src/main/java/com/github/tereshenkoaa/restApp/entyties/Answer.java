package com.github.tereshenkoaa.restApp.entyties;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
public class Answer extends BaseEntity {

    @Column
    private String name;
    @JoinColumn(name = "question_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Question question;
    @Column
    private Boolean isCorrect;
    @Column     //пометка на удаление
    private Boolean isMark;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    public Boolean getMark() {
        return isMark;
    }

    public void setMark(Boolean mark) {
        isMark = mark;
    }
}
