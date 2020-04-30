package com.github.tereshenkoaa.restApp.service;

import com.github.tereshenkoaa.restApp.controller.dto.JournalItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalRequestDTO;
import com.github.tereshenkoaa.restApp.entyties.Journal;

import java.util.List;

public interface JournalService {
    Journal getJOurnal(String id);

    List<? extends JournalItemDTO> getJournalRows(String id, JournalRequestDTO reg);

    int getCountRows(String id);
}
