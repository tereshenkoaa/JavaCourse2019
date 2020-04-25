package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.RestAppApplication;
import com.github.tereshenkoaa.restApp.controller.dto.AnswerItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalRequestDTO;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.JournalRepository;
import com.github.tereshenkoaa.restApp.data.QuestionRepository;
import com.github.tereshenkoaa.restApp.data.SessionRepository;
import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Journal;
import com.github.tereshenkoaa.restApp.entyties.Question;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestAppApplication.class})
@WebAppConfiguration
@SpringBootTest
public class JournalServiceImplTest {

    @Mock
    private JournalRepository journalRepository;

    //@Mock
    @Autowired
    private AnswerRepository answerRepository;

    //@Mock
    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private JournalService journalService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void getJOurnal() {

        Journal journal = new Journal();
        journal.setId("questions");
        journal.setName("Вопросы");
        journal.setDefaultPageSize(Long.parseLong("15"));

        Mockito.when(journalRepository.findById(Mockito.any())).thenReturn(Optional.of(journal));

        Journal actual = journalService.getJOurnal("questions");

        assertEquals("Вопросы",actual.getName());

    }

    @Test
    public void getJournalRows() {

        Question question = new Question();
        question.setId(Long.parseLong("1"));
        question.setName("Test question");

        List<Question> listQuestions = new ArrayList<Question>();
        listQuestions.add(question);

        JournalRequestDTO journalRequestDTO = new JournalRequestDTO();
        journalRequestDTO.search = "";
        journalRequestDTO.page = 1;
        journalRequestDTO.pageSize = 5;

        List<? extends JournalItemDTO> actual = journalService.getJournalRows("questions",journalRequestDTO);

        assertEquals(5,actual.size());

    }

    @Test
    public void testPage() {
        Pageable pageRequest = PageRequest.of(1,5, Sort.Direction.ASC,"id");
        List<Question> page = questionRepository.findByNameContainingIgnoreCase("",pageRequest);

        assertEquals(5,page.size());
    }
}