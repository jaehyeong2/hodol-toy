package jjfactory.hodol.service;

import jjfactory.hodol.domain.Assignment;
import jjfactory.hodol.repository.AssignmentRepository;
import jjfactory.hodol.req.SubjectCreate;
import jjfactory.hodol.res.AssignmentRes;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public AssignmentRes get(Long assignmentId){
        Assignment assignment = assignmentRepository.findById(assignmentId).orElseThrow(() -> {
            throw new IllegalArgumentException("조회 실패");
        });

        return new AssignmentRes(assignment);
    }

    public String write(SubjectCreate dto){
        Assignment assignment = Assignment.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();

        assignmentRepository.save(assignment);
        return "Y";
    }
}
