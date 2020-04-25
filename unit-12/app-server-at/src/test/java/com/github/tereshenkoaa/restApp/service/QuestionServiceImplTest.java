package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.RestAppApplication;
import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.QuestionRepository;
import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration
@SpringBootTest
public class QuestionServiceImplTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuestionService questionService;

    @Mock
    private AnswerRepository answerRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createListQuestionTest(){

        Mockito.when(answerRepository.findByQuestionAndIsMarkFalse(Mockito.any())).thenReturn(new ArrayList<Answer>());

        List<QuestionItemDTO> actual = questionService.createListQuestion();

        assertEquals(10,actual.size());
    }

}