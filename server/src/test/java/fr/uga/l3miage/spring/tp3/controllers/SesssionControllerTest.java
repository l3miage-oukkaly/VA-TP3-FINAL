package fr.uga.l3miage.spring.tp3.controllers;

import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationStepRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import fr.uga.l3miage.spring.tp3.repositories.ExamRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;


@AutoConfigureTestDatabase
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")

public class SesssionControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;


    // les repositories
    @Autowired
    private ExamRepository examRepository;
    @Autowired
    private EcosSessionRepository ecosSessionRepository;
    @Autowired
    private EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;
    @Autowired
    private EcosSessionProgrammationRepository ecosSessionProgrammationRepository;

    @AfterEach
    public void clear() {
        examRepository.deleteAll();
        ecosSessionRepository.deleteAll();
        ecosSessionProgrammationStepRepository.deleteAll();
        ecosSessionProgrammationRepository.deleteAll();
    }

    @Test
    void canCreateSession(){
        // TODO
    }



}
