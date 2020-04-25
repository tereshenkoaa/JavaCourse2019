package com.github.tereshenkoaa.restApp.controller;

import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import com.github.tereshenkoaa.restApp.service.QuestionService;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("api/question")
public class QuestionRestController {

    private final QuestionService questionService;

    public QuestionRestController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping("create")
    public QuestionItemDTO create(@RequestBody QuestionItemDTO dto) {
        return questionService.createQuestion(dto);
    }

    @PutMapping("edit")
    public QuestionItemDTO edit(@RequestBody QuestionItemDTO dto) {
        return questionService.editQuestion(dto);
    }
}
