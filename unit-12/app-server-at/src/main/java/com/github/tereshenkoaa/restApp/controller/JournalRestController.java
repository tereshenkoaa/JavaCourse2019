package com.github.tereshenkoaa.restApp.controller;

import com.github.tereshenkoaa.restApp.controller.dto.JournalEntityDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalItemDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalRequestDTO;
import com.github.tereshenkoaa.restApp.controller.dto.JournalResultDTO;
import com.github.tereshenkoaa.restApp.service.JournalService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("api/journal")
public class JournalRestController {

    public JournalRestController(JournalService journalService) {
        this.journalService = journalService;
    }

    private final JournalService journalService;

    @GetMapping("{id}")
    public JournalEntityDTO getJournalEntity(@PathVariable String id) {
        return new JournalEntityDTO(journalService.getJOurnal(id));
    }

    @PutMapping("{id}/rows")
    public JournalResultDTO getRows(@PathVariable String id, @RequestBody JournalRequestDTO req) {
        List<? extends JournalItemDTO> collect = journalService.getJournalRows(id, req);
        return  new JournalResultDTO(journalService.getCountRows(id),collect);
    }

}
