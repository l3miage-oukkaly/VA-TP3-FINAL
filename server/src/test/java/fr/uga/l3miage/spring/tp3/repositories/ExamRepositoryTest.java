package fr.uga.l3miage.spring.tp3.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureTestDatabase()
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, properties = "spring.jpa.database-platform=org.hibernate.dialect.H2Dialect")

public class ExamRepositoryTest {
    @Autowired
    private ExamRepository examRepository;

    @BeforeEach
    public void clear(){
        examRepository.deleteAll();
    }
    //    Set<ExamEntity> findAllBySkillEntitiesContaining(SkillEntity skillEntity);

    @Test
    void testFindAllBySkillEntitiesContaining(){

    }
}
