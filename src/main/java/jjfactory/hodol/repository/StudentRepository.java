package jjfactory.hodol.repository;

import jjfactory.hodol.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
