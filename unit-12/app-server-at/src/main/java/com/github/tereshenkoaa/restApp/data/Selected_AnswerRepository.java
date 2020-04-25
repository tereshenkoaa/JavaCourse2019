package com.github.tereshenkoaa.restApp.data;

import com.github.tereshenkoaa.restApp.entyties.Answer;
import com.github.tereshenkoaa.restApp.entyties.Selected_Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface Selected_AnswerRepository extends CrudRepository<Selected_Answer,Long> {


}
