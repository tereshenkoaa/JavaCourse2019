package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.data.Selected_AnswerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class Selected_AnswerServiceImpl implements Selected_AnswerService {

    private final Selected_AnswerRepository selected_answerRepository;

    public Selected_AnswerServiceImpl(Selected_AnswerRepository selected_answerRepository) {
        this.selected_answerRepository = selected_answerRepository;
    }


}
