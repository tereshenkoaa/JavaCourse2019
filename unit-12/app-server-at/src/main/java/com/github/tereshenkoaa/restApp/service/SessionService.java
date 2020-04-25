package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.AnsweredQuestionDTO;
import com.github.tereshenkoaa.restApp.entyties.Session;

public interface SessionService {

    Session createSession(AnsweredQuestionDTO dto);

}
