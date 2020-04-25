package com.github.tereshenkoaa.restApp.data;

import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Question;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.math.BigDecimal;
import java.text.StringCharacterIterator;
import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Long> {

    List<Answer> findByQuestionAndIsMarkFalse(Question question);

    @Modifying(clearAutomatically = true)
    @Query(
            value = "UPDATE answer SET is_mark = true where question_id = ?1",
            nativeQuery = true
    )
    void deleteByQuestionId(String id);

    @Query(
            value = "SELECT count(*) FROM answer where question_id = ?1 and is_mark = false",
            nativeQuery = true
    )
    BigDecimal getCountAnswers(String id);

    @Query(
            value = "SELECT * FROM answer where question_id = ?1 AND is_correct = 'true' and is_mark = false",
            nativeQuery = true
    )
    List<Answer> getCorrectAnswers(String id);

}


