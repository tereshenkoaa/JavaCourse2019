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

        Double res = 0.00;
        Double countQuestions = 0.00;
        
        //запись выбранных ответов
        for (SessionQuestionListDTO question : dto.questionsList) {

            countQuestions += 1.00;
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

            Double delta = 0.00;

            if (correctAnswers.size() == 1) {
                if (selectedAnswers.size() == 1 ) {
                    if (correctAnswers.get(0).equals(selectedAnswers.get(0))) {
                        delta = 1.00;
                        //delta = new BigDecimal("1");
                    }
                }
            } else {

                Double k = 0.00;                                                        //количество выбранных верных ответов
                Double w = 0.00;                                                        //количество выбранных НЕверных ответов
                Double n = answerRepository.getCountAnswers(question.id);               //общее количество вариантов
                Double m = Double.parseDouble(String.valueOf(correctAnswers.size()));   //общее количество верных ответов

                if (n.compareTo(m) == 1) {
                    delta += 1.00;
                } else {
                    for (Answer selectedAnswer : selectedAnswers) {
                        if (correctAnswers.indexOf(selectedAnswer) == -1) {
                            //выбран неверный ответ
                            w += 1.00;
                        } else {
                            k += 1.00;
                        }
                    }
                    delta = Double.max(0.00,k/m - w/(n - m));
                }

            }

            res += delta;

        }

        try {
            res = res * 100/countQuestions;
        } catch (Exception e) {
            res = 0.00;
        }


        session.setPercent(res);
        sessionRepository.save(session);

        return session;

    }
}
