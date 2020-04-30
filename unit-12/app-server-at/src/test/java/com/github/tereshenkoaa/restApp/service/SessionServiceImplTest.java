package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.RestAppApplication;
import com.github.tereshenkoaa.restApp.controller.dto.AnsweredQuestionDTO;
import com.github.tereshenkoaa.restApp.controller.dto.SessionQuestionAnswerDTO;
import com.github.tereshenkoaa.restApp.controller.dto.SessionQuestionListDTO;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.Selected_AnswerRepository;
import com.github.tereshenkoaa.restApp.data.SessionRepository;
import com.github.tereshenkoaa.restApp.entyties.Selected_Answer;
import com.github.tereshenkoaa.restApp.entyties.Session;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration
@SpringBootTest
public class SessionServiceImplTest {

    @Autowired
    private SessionService sessionService;
    @Autowired
    private AnswerRepository answerRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private Selected_AnswerRepository selected_answerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createSession() {

        //сформируем dto
        List<SessionQuestionListDTO> sessionQuestionListDTO = new ArrayList<SessionQuestionListDTO>();

        List<SessionQuestionAnswerDTO> answersList1 = new ArrayList<SessionQuestionAnswerDTO>();
        List<SessionQuestionAnswerDTO> answersList2 = new ArrayList<SessionQuestionAnswerDTO>();
        List<SessionQuestionAnswerDTO> answersList3 = new ArrayList<SessionQuestionAnswerDTO>();

        SessionQuestionAnswerDTO answer1 = new SessionQuestionAnswerDTO("1",Boolean.FALSE);
        SessionQuestionAnswerDTO answer2 = new SessionQuestionAnswerDTO("2",Boolean.TRUE);
        SessionQuestionAnswerDTO answer3 = new SessionQuestionAnswerDTO("3",Boolean.FALSE);

        SessionQuestionAnswerDTO answer4 = new SessionQuestionAnswerDTO("4",Boolean.FALSE);
        SessionQuestionAnswerDTO answer5 = new SessionQuestionAnswerDTO("5",Boolean.TRUE);
        SessionQuestionAnswerDTO answer6 = new SessionQuestionAnswerDTO("6",Boolean.FALSE);

        SessionQuestionAnswerDTO answer7 = new SessionQuestionAnswerDTO("33",Boolean.FALSE);
        SessionQuestionAnswerDTO answer8 = new SessionQuestionAnswerDTO("34",Boolean.TRUE);
        SessionQuestionAnswerDTO answer9 = new SessionQuestionAnswerDTO("35",Boolean.FALSE);

        answersList1.add(answer1);
        answersList1.add(answer2);
        answersList1.add(answer3);

        answersList2.add(answer4);
        answersList2.add(answer5);
        answersList2.add(answer6);

        answersList3.add(answer7);
        answersList3.add(answer8);
        answersList3.add(answer9);

        SessionQuestionListDTO questionListDTO1 = new SessionQuestionListDTO();
        questionListDTO1.id = "41";
        questionListDTO1.answersList = answersList1;

        SessionQuestionListDTO questionListDTO2 = new SessionQuestionListDTO();
        questionListDTO2.id = "37";
        questionListDTO2.answersList = answersList2;

        SessionQuestionListDTO questionListDTO3 = new SessionQuestionListDTO();
        questionListDTO3.id = "33";
        questionListDTO3.answersList = answersList3;

        sessionQuestionListDTO.add(questionListDTO1);
        sessionQuestionListDTO.add(questionListDTO2);
        sessionQuestionListDTO.add(questionListDTO3);

        AnsweredQuestionDTO answeredQuestionDTO = new AnsweredQuestionDTO();
        answeredQuestionDTO.name = "Test user";
        answeredQuestionDTO.questionsList = sessionQuestionListDTO;

        Mockito.when(selected_answerRepository.save(Mockito.any())).thenReturn(new Selected_Answer());

        Session newSession = sessionService.createSession(answeredQuestionDTO);

        assertEquals(Double.parseDouble("100"),newSession.getPercent(),0.01);

    }

    @Test
    public void test1() {

        //пример мока на save

        Session session1 = new Session();
        session1.setId(Long.parseLong("2"));
        session1.setName("Test user");
        session1.setPercent(Double.parseDouble("75.00"));

        Session session2 = new Session();
        session2.setId(Long.parseLong("3"));
        session2.setName("Test user 3");
        session2.setPercent(Double.parseDouble("95.00"));

        Mockito.when(sessionRepository.save(Mockito.any())).thenReturn(session2);

        Session session3 = sessionRepository.save(session1);

        assertEquals(session2.getName(),session3.getName());


    }
}