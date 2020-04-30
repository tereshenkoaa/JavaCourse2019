package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.AnswerItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.QuestionRepository;
import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Question;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, AnswerRepository answerRepository) {
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
    }

    @Override
    public QuestionItemDTO createQuestion(QuestionItemDTO dto) {
        Question question = new Question();
        question.setName(dto.name);
        questionRepository.save(question);

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);
            answer.setMark(Boolean.FALSE);
            answerRepository.save(answer);
        }

        return new QuestionItemDTO(question, answerRepository.findByQuestionAndIsMarkFalse(question));
    }

    @Override
    public QuestionItemDTO editQuestion(QuestionItemDTO dto) {

        Long id = Long.parseLong(dto.id);

        Question question = new Question();
        question.setId(id);
        question.setName(dto.name);
        questionRepository.save(question);

        answerRepository.deleteByQuestionId(dto.id);

        for (AnswerItemDTO answerDTO : dto.answers) {
            Answer answer = new Answer();
            answer.setName(answerDTO.answerText);
            answer.setCorrect(answerDTO.isCorrect);
            answer.setQuestion(question);
            answer.setMark(Boolean.FALSE);
            answerRepository.save(answer);
        }

        return new QuestionItemDTO(question, answerRepository.findByQuestionAndIsMarkFalse(question));

    }

    @Override
    public List<QuestionItemDTO> createListQuestion() {
        return questionRepository.createListQuestion()
                .stream()
                .map(question -> new QuestionItemDTO(question, answerRepository.findByQuestionAndIsMarkFalse(question),Boolean.FALSE))
                .collect(Collectors.toList());
    }
}
