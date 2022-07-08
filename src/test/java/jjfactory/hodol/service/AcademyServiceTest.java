package jjfactory.hodol.service;

import jjfactory.hodol.domain.Academy;
import jjfactory.hodol.domain.Subject;
import jjfactory.hodol.repository.AcademyRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class AcademyServiceTest {

    @Autowired
    AcademyRepository academyRepository;

    @Autowired
    AcademyService academyService;

    @AfterAll
    public void clean(){
        academyRepository.deleteAll();
    }

    @Test
    @DisplayName("아카데미_여러개조회시_N+1")
    void findAllSubjectsName() {
        List<Academy> academies = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            Academy academy = Academy.builder()
                    .name("school" + i)
                    .build();

            academy.addSubjects(Subject.builder().name("web development" + i).build());
            academies.add(academy);
        }

        academyRepository.saveAll(academies);

        List<String> allSubjectsName = academyService.findAllSubjectsName();

        assertThat(allSubjectsName.size()).isEqualTo(10);
    }
}