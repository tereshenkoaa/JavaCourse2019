package com.github.tereshenkoaa.restApp.data;

import com.github.tereshenkoaa.restApp.entyties.Journal;
import org.springframework.data.repository.CrudRepository;

public interface JournalRepository extends CrudRepository<Journal,String> {

}
