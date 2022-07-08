package jjfactory.hodol.service;

import jjfactory.hodol.domain.Assignment;
import jjfactory.hodol.repository.AssignmentRepository;
import jjfactory.hodol.req.SubjectCreate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;

    public String write(SubjectCreate dto){
        Assignment assignment = new Assignment(dto.getTitle(), dto.getContent());
        assignmentRepository.save(assignment);
        return "Y";
    }
}
