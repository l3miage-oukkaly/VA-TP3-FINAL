package fr.uga.l3miage.spring.tp3.controllers;

import fr.uga.l3miage.spring.tp3.components.CandidateComponent;
import fr.uga.l3miage.spring.tp3.components.SessionComponent;
import fr.uga.l3miage.spring.tp3.exceptions.CandidatNotFoundResponse;
import fr.uga.l3miage.spring.tp3.exceptions.handlers.CreationSessionExceptionHandler;
import fr.uga.l3miage.spring.tp3.exceptions.rest.CreationSessionRestException;
import fr.uga.l3miage.spring.tp3.exceptions.technical.ExamNotFoundException;
import fr.uga.l3miage.spring.tp3.models.EcosSessionEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationEntity;
import fr.uga.l3miage.spring.tp3.models.EcosSessionProgrammationStepEntity;
import fr.uga.l3miage.spring.tp3.repositories.*;
import fr.uga.l3miage.spring.tp3.request.SessionCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationCreationRequest;
import fr.uga.l3miage.spring.tp3.request.SessionProgrammationStepCreationRequest;
import fr.uga.l3miage.spring.tp3.responses.EcosSessionProgrammationResponse;
import fr.uga.l3miage.spring.tp3.responses.EcosSessionProgrammationStepResponse;
import fr.uga.l3miage.spring.tp3.responses.SessionResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;


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
    @SpyBean
    private  SessionComponent sessionComponent;

    @AfterEach
    public void clear() {
        examRepository.deleteAll();
        ecosSessionRepository.deleteAll();
        ecosSessionProgrammationStepRepository.deleteAll();
        ecosSessionProgrammationRepository.deleteAll();
    }

    @Test
    void canCreateSession(){
        final HttpHeaders headers = new HttpHeaders();
        final SessionProgrammationStepCreationRequest sessionProgrammationStepCreationRequest = SessionProgrammationStepCreationRequest
                .builder()
                .code("101")
                .description("Yo mec")
                .build();
        final SessionProgrammationCreationRequest sessionProgrammationCreationRequest = SessionProgrammationCreationRequest
                .builder()
                .label("Label")
                .steps(Set.of(sessionProgrammationStepCreationRequest))
                .build();
        final SessionCreationRequest sessionCreationRequest = SessionCreationRequest
                .builder()
                .name("My Session")
                .examsId(Set.of())
                .ecosSessionProgrammation(sessionProgrammationCreationRequest)
                .build();

        ResponseEntity<SessionResponse> response = testRestTemplate.exchange("/api/sessions/create", HttpMethod.POST, new HttpEntity<>(sessionCreationRequest, headers), SessionResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(ecosSessionRepository.count()).isEqualTo(1);
        assertThat(ecosSessionProgrammationRepository.count()).isEqualTo(1);
        assertThat(ecosSessionProgrammationStepRepository.count()).isEqualTo(1);
        verify(sessionComponent, times(1)).createSession(any(EcosSessionEntity.class));
    }

}
