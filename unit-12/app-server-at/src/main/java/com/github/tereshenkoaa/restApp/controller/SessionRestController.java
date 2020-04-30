package com.github.tereshenkoaa.restApp.controller;

import com.github.tereshenkoaa.restApp.controller.dto.AnsweredQuestionDTO;
import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import com.github.tereshenkoaa.restApp.entyties.Session;
import com.github.tereshenkoaa.restApp.service.QuestionService;
import com.github.tereshenkoaa.restApp.service.SessionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/session")
public class SessionRestController {

    private final SessionService sessionService;
    private final QuestionService questionService;

    public SessionRestController(SessionService sessionService, QuestionService questionService) {
        this.sessionService = sessionService;
        this.questionService = questionService;
    }

    @PostMapping("")
    public String create(@RequestBody AnsweredQuestionDTO req) {

        Session session = sessionService.createSession(req);
        return session.getPercent().toString();

    }

    @GetMapping("questions-new")
    public List<QuestionItemDTO> createListQuestion() {
        return questionService.createListQuestion();
    }

}
