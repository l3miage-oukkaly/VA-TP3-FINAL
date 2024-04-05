package fr.uga.l3miage.spring.tp3.repositories;

import fr.uga.l3miage.spring.tp3.enums.TestCenterCode;
import fr.uga.l3miage.spring.tp3.models.CandidateEntity;
import fr.uga.l3miage.spring.tp3.models.CandidateEvaluationGridEntity;
import fr.uga.l3miage.spring.tp3.models.TestCenterEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@AutoConfigureTestDatabase()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")
public class CandidateRepositoryTest {
    @Autowired
    private CandidateRepository candidateRepository;

    @Autowired
    private CandidateEvaluationGridRepository candidateEvaluationGridRepository;

    @Autowired
    private TestCenterRepository testCenterRepository;

    @BeforeEach
    public void clear(){
        candidateEvaluationGridRepository.deleteAll();
        candidateRepository.deleteAll();
        testCenterRepository.deleteAll();
    }


    //Set<CandidateEntity> findAllByTestCenterEntityCode(TestCenterCode code);
    @Test
    void testFindAllByTestCenterEntityCode(){
        TestCenterEntity testCenterEntity = TestCenterEntity.builder()
                .code(TestCenterCode.PAR)
                .build();

        testCenterRepository.save(testCenterEntity);
        //given
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .firstname("test")
                .email("test@gmail.com")
                .testCenterEntity(testCenterEntity)
                .build();

        candidateRepository.save(candidateEntity);
        //when
        Set<CandidateEntity> candidateEntities = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.PAR);
        Set<CandidateEntity> noCandidateEntities = candidateRepository.findAllByTestCenterEntityCode(TestCenterCode.NCE);
        //then
        assert(candidateEntities.size() == 1);
        assert(noCandidateEntities.size() == 0);
    }
    //Set<CandidateEntity> findAllByCandidateEvaluationGridEntitiesGradeLessThan(double grade);

    @Test
    void testFindAllByCandidateEvaluationGridEntitiesGradeLessThan(){
        //given
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .firstname("test")
                .email("test2@gmail.com")
                //.candidateEvaluationGridEntities(Set.of(candidateEvaluationGridEntity))
                .hasExtraTime(false)
                .build();

        candidateRepository.save(candidateEntity);

        CandidateEvaluationGridEntity candidateEvaluationGridEntity = CandidateEvaluationGridEntity.builder()
                .grade(5)
                .candidateEntity(candidateEntity)
                .build();

        candidateEvaluationGridRepository.save(candidateEvaluationGridEntity);

        candidateEntity.setCandidateEvaluationGridEntities(Set.of(candidateEvaluationGridEntity));
        candidateRepository.save(candidateEntity);


        List<CandidateEntity> candidates = candidateRepository.findAll();
        List<CandidateEvaluationGridEntity> grades = candidateEvaluationGridRepository.findAll();

        //when
        Set<CandidateEntity> candidateEntities = candidateRepository.findAllByCandidateEvaluationGridEntitiesGradeLessThan(10);
        Set<CandidateEntity> noCandidateEntities = candidateRepository.findAllByCandidateEvaluationGridEntitiesGradeLessThan(4);
        //then
        assertThat(candidateEntities).hasSize(1);
        assertThat(noCandidateEntities).hasSize(0);
    }

    //Set<CandidateEntity> findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate localDate);
    @Test
    void testFindAllByHasExtraTimeFalseAndBirthDateBefore(){
        //given
        CandidateEntity candidateEntity = CandidateEntity.builder()
                .firstname("test")
                .email("test3@gmail.com")
                .hasExtraTime(false)
                .birthDate(LocalDate.of(1990,1,1))
                .build();

        candidateRepository.save(candidateEntity);
        //when
        Set<CandidateEntity> candidateEntities = candidateRepository.findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate.of(1991,1,1));
        Set<CandidateEntity> noCandidateEntities = candidateRepository.findAllByHasExtraTimeFalseAndBirthDateBefore(LocalDate.of(1989,1,1));
        //then
        assertThat(candidateEntities).hasSize( 1);
        assert(noCandidateEntities.size() == 0);
    }
}
