package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuestionService {
    QuestionItemDTO createQuestion(QuestionItemDTO dto);

    QuestionItemDTO editQuestion(QuestionItemDTO dto);

    List<QuestionItemDTO> createListQuestion();
}
