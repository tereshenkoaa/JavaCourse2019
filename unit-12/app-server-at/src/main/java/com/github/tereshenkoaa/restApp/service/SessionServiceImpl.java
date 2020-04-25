package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.*;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.QuestionRepository;
import com.github.tereshenkoaa.restApp.data.Selected_AnswerRepository;
import com.github.tereshenkoaa.restApp.data.SessionRepository;
import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Question;
import com.github.tereshenkoaa.restApp.entyties.Selected_Answer;
import com.github.tereshenkoaa.restApp.entyties.Session;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SessionServiceImpl implements SessionService {

    private final SessionRepository sessionRepository;
    private final AnswerRepository answerRepository;
    private final QuestionRepository questionRepository;
    private final Selected_AnswerRepository selected_answerRepository;

    public SessionServiceImpl(SessionRepository sessionRepository,
                              AnswerRepository answerRepository,
                              QuestionRepository questionRepository,
                              Selected_AnswerRepository selected_answerRepository) {
        this.sessionRepository = sessionRepository;
        this.answerRepository = answerRepository;
        this.questionRepository = questionRepository;
        this.selected_answerRepository = selected_answerRepository;
    }

    @Override
    public Session createSession(AnsweredQuestionDTO dto) {

        Session session = new Session();
        session.setName(dto.name);

        sessionRepository.save(session);

        BigDecimal res = new BigDecimal("0");
        BigDecimal countQuestions = new BigDecimal("0");

        //запись выбранных ответов
        for (SessionQuestionListDTO question : dto.questionsList) {

            countQuestions = countQuestions.add(new BigDecimal(1));
            List<Answer> selectedAnswers = new ArrayList<Answer>();

            for (SessionQuestionAnswerDTO answer : question.answersList) {
                if (answer.isSelected == true) {

                    Answer currentAnswer = answerRepository.findById(Long.parseLong(answer.id)).get();

                    Selected_Answer selected_answer = new Selected_Answer();
                    selected_answer.setAnswer(currentAnswer);
                    selected_answer.setSession(session);
                    selected_answerRepository.save(selected_answer);

                    selectedAnswers.add(currentAnswer);
                }
            }

            Question currentQuestion = questionRepository.findById(Long.parseLong(question.id)).get();
            List<Answer> correctAnswers = answerRepository.getCorrectAnswers(question.id);

            BigDecimal delta = new BigDecimal("0");

            if (correctAnswers.size() == 1) {
                if (selectedAnswers.size() == 1 ) {
                    if (correctAnswers.get(0).equals(selectedAnswers.get(0))) {
                        delta = new BigDecimal("1");
                    }
                }
            } else {
                BigDecimal k = new BigDecimal("0");    //количество выбранных верных ответов
                BigDecimal w = new BigDecimal("0");    //количество выбранных НЕверных ответов
                BigDecimal n = answerRepository.getCountAnswers(question.id);    //общее количество вариантов -> возьмем через сервис
                BigDecimal m = new BigDecimal(correctAnswers.size()); //общее количество верных ответов

                for (Answer selectedAnswer : selectedAnswers) {
                    if (correctAnswers.indexOf(selectedAnswer) == -1) {
                        //выбран неверный ответ
                        w = w.add(new BigDecimal(1));
                    } else {
                         k = k.add(new BigDecimal(1));

                    }
                }
                n = n.subtract(m);
                w = w.multiply(n);
                k = k.divide(m);
                delta = k.subtract(w);
                if (delta.signum() == -1) delta = new BigDecimal("0");
            }

            res = res.add(delta);

        }

        res = res.multiply(new BigDecimal(100));
        res = res.divide(countQuestions, RoundingMode.CEILING);

        session.setPercent(res.doubleValue());
        sessionRepository.save(session);

        return session;

    }
}
