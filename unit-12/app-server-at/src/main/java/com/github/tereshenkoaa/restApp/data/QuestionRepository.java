package com.github.tereshenkoaa.restApp.data;

import com.github.tereshenkoaa.restApp.entyties.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends CrudRepository<Question,Long>,
                                            PagingAndSortingRepository<Question,Long> {

    List<Question> findByNameContainingIgnoreCase(String search, Pageable pageRequest);

    @Query(
            value = "SELECT * FROM question ORDER BY rand() LIMIT 10",
            nativeQuery = true
    )
    List<Question> createListQuestion();

    @Query(
            value = "SELECT Q.* FROM QUESTION Q LEFT JOIN ANSWER A ON Q.ID = A.QUESTION_ID " +
                    "WHERE A.IS_MARK = FALSE GROUP By Q.Id HAVING Count(a.*) = ?1",
            nativeQuery = true
    )
    List<Question> filterByNameAndCount(String filterValue, Pageable pageRequest);
}
