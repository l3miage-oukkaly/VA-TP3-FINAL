package fr.uga.l3miage.spring.tp3.components;

import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionProgrammationStepRepository;
import fr.uga.l3miage.spring.tp3.repositories.EcosSessionRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@AutoConfigureTestDatabase
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)

public class SessionComponentTest {

    @Autowired
    private SessionComponent sessionComponent;

    @MockBean
    private EcosSessionProgrammationRepository ecosSessionProgrammationRepository;

    @MockBean
    private EcosSessionProgrammationStepRepository ecosSessionProgrammationStepRepository;

    @MockBean
    private EcosSessionRepository ecosSessionRepository;

    /*
     public EcosSessionEntity createSession(EcosSessionEntity entity){
        ecosSessionProgrammationStepRepository.saveAll(entity.getEcosSessionProgrammationEntity().getEcosSessionProgrammationStepEntities());
        ecosSessionProgrammationRepository.save(entity.getEcosSessionProgrammationEntity());
        return ecosSessionRepository.save(entity);
    }
     */
    @Test
    void testCreateSession(){
        // when(ecosSessionRepository.save(ecosSessionEntity)).thenReturn(ecosSessionEntity);
        // when(ecosSessionProgrammationRepository.save(ecosSessionEntity.getEcosSessionProgrammationEntity())).thenReturn(ecosSessionEntity.getEcosSessionProgrammationEntity());
        // when(ecosSessionProgrammationStepRepository.saveAll(ecosSessionEntity.getEcosSessionProgrammationEntity().getEcosSessionProgrammationStepEntities())).thenReturn(ecosSessionEntity.getEcosSessionProgrammationEntity().getEcosSessionProgrammationStepEntities());
        // assertDoesNotThrow(()->sessionComponent.createSession(ecosSessionEntity));
    }



}
