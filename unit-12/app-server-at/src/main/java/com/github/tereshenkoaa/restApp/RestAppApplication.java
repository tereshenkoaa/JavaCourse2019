package com.github.tereshenkoaa.restApp;

import com.github.tereshenkoaa.restApp.data.JournalRepository;
import com.github.tereshenkoaa.restApp.entyties.Journal;
import com.github.tereshenkoaa.restApp.service.JournalService;
import com.github.tereshenkoaa.restApp.service.JournalServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class RestAppApplication {

	@Autowired
	private JournalRepository journalRepository;

	public static void main(String[] args) {
		SpringApplication.run(RestAppApplication.class, args);
	}

	@PostConstruct
	private void Initdata() {
		Journal journal = new Journal();
		journal.setId(JournalServiceImpl.QUESTIONS_JOURNAL_ID);
		journal.setName("Вопросы");
		journal.setDefaultPageSize((long) 5);
		journalRepository.save(journal);

		Journal journal2 = new Journal();
		journal2.setId(JournalServiceImpl.SESSIONS_JOURNAL_ID);
		journal2.setName("Сессии");
		journal2.setDefaultPageSize((long) 5);
		journalRepository.save(journal2);

	}

}
