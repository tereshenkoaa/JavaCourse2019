package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.RestAppApplication;
import com.github.tereshenkoaa.restApp.controller.dto.AnswerItemDTO;
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
public class QuestionServiceImplTest2 {

    @Mock
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
    public void createQuestion() {

        Question question = new Question();
        question.setId(Long.parseLong("1"));
        question.setName("Test question");

        Mockito.when(answerRepository.findByQuestionAndIsMarkFalse(Mockito.any())).thenReturn(new ArrayList<Answer>());
        Mockito.when(questionRepository.save(Mockito.any())).thenReturn(question);
        Mockito.when(answerRepository.save(Mockito.any())).thenReturn(new Answer());

        Answer answer1 = new Answer();
        answer1.setId(Long.parseLong("1"));
        answer1.setName("Answer 1");
        answer1.setQuestion(question);
        answer1.setMark(Boolean.FALSE);

        Answer answer2 = new Answer();
        answer2.setId(Long.parseLong("2"));
        answer2.setName("Answer 2");
        answer2.setQuestion(question);
        answer2.setMark(Boolean.FALSE);

        List<AnswerItemDTO> answersList = new ArrayList<AnswerItemDTO>();
        answersList.add(new AnswerItemDTO(answer1));
        answersList.add(new AnswerItemDTO(answer2));

        QuestionItemDTO questionItemDTO = new QuestionItemDTO();
        questionItemDTO.name = "Test question dto";
        questionItemDTO.answers = answersList;

        QuestionItemDTO actual = questionService.createQuestion(questionItemDTO);

        assertEquals("Test question dto",actual.name);
        assertEquals(2,actual.answers.size());

    }

}