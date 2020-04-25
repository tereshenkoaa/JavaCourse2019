package com.github.tereshenkoaa.restApp.controller.dto;

import java.util.List;

public class JournalRequestDTO {

    public String search;
    public int page;
    public int pageSize;
    public List<JournalFiltresDTO> filters;

    public JournalRequestDTO() {
    }

}