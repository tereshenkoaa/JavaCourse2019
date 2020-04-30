package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.JournalItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalRequestDTO;
import com.github.tereshenkoaa.restApp.controller.dto.QuestionItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.SessionItemDTO;
import com.github.tereshenkoaa.restApp.data.AnswerRepository;
import com.github.tereshenkoaa.restApp.data.JournalRepository;
import com.github.tereshenkoaa.restApp.data.QuestionRepository;
import com.github.tereshenkoaa.restApp.data.SessionRepository;
import com.github.tereshenkoaa.restApp.entyties.BaseEntity;
import com.github.tereshenkoaa.restApp.entyties.Journal;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class JournalServiceImpl implements JournalService {

    public static final String QUESTIONS_JOURNAL_ID = "questions";
    public static final String SESSIONS_JOURNAL_ID = "sessions";

    private final JournalRepository journalRepository;
    private final QuestionRepository questionRepository;
    private final AnswerRepository answerRepository;
    private final SessionRepository sessionRepository;

    public JournalServiceImpl(JournalRepository journalRepository, QuestionRepository questionRepository, AnswerRepository answerRepository, SessionRepository sessionRepository) {
        this.journalRepository = journalRepository;
        this.questionRepository = questionRepository;
        this.answerRepository = answerRepository;
        this.sessionRepository = sessionRepository;
    }

    @Override
    public Journal getJOurnal(String id) {
        return journalRepository.findById(id)
                .orElseThrow(()->new RuntimeException(String.format(
                        "Не найден журнал с id = %s",id
                )));
    }

    @Override
    public List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO req) {

        List<? extends JournalItemDTO> collect;
        String filterValue = "";

        if (req.filters.size() == 1) {
            filterValue = req.filters.get(0).value;
        }

        Pageable pageRequest = PageRequest.of(req.page-1,req.pageSize, Sort.Direction.ASC,"id");

        switch (id) {
            case QUESTIONS_JOURNAL_ID:
                if (filterValue.length() > 0) {
                    collect = questionRepository.filterByNameAndCount(filterValue,pageRequest)
                                            .stream()
                            .map(question -> new QuestionItemDTO(question, answerRepository.findByQuestionAndIsMarkFalse(question)))
                    .collect(Collectors.toList());

                } else {
                    collect = getCollect(
                            req.search,
                            pageRequest,
                            questionRepository::findByNameContainingIgnoreCase,
                            question -> new QuestionItemDTO(question, answerRepository.findByQuestionAndIsMarkFalse(question)));
                }
                break;
            case SESSIONS_JOURNAL_ID:
                collect = getCollect(
                        req.search,
                        pageRequest,
                        sessionRepository::findByNameContainingIgnoreCase,
                        session -> new SessionItemDTO(session));
                break;
            default:
                throw new RuntimeException("Не смогли определить тип журнала");
        }


        return collect;

    }

    @Override
    public int getCountRows(String id) {
        switch (id) {
            case QUESTIONS_JOURNAL_ID:
                return (int) questionRepository.count();
                //break;
            case SESSIONS_JOURNAL_ID:
                return (int) sessionRepository.count();
                //break;
            default:
                return 0;
        }
    }


    private<T extends BaseEntity, U extends JournalItemDTO> List<U> getCollect(
            String search,
            Pageable pageRequest,
            BiFunction<String, Pageable, List<T>> finder,
            Function<T,U> mapper
            ) {

        return finder.apply(search,pageRequest)
                .stream().map(mapper).collect(Collectors.toList());

    }

}
