package jjfactory.hodol.service;

import jjfactory.hodol.domain.Academy;
import jjfactory.hodol.repository.AcademyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class AcademyService {
    private final AcademyRepository academyRepository;

    @Transactional()
    public List<String> findAllSubjectsName(){
        return extractSubjectName(academyRepository.findAll());
    }

    /**
     * Lazy Load를 수행하기 위해 메소드를 별도로 생성
     */
    private List<String> extractSubjectName(List<Academy> academies){
       log.info(" ====== 모든 과목 추출 =====");
       log.info("Academy size() : {}", academies.size());

       return academies.stream().map(a ->
               a.getSubjects().get(0).getName())
               .collect(Collectors.toList());
    }
}
