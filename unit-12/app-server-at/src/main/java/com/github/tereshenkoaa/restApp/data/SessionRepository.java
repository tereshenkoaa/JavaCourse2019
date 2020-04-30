package com.github.tereshenkoaa.restApp.data;

import com.github.tereshenkoaa.restApp.entyties.Session;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface SessionRepository extends CrudRepository<Session,Long>,
        PagingAndSortingRepository<Session,Long> {

    List<Session> findByNameContainingIgnoreCase(String s, Pageable pageRequest);
}
