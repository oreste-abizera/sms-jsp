package rw.ac.rca.nat2022.server.services;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Student;
import rw.ac.rca.nat2022.server.utils.dtos.StudentDTO;

import java.util.List;

public interface IStudentService {

    List<Student> getAllStudents();

    Student save(StudentDTO studentDTO);

    Student update(Long id, StudentDTO studentDTO);

    Student getStudentById(Long id);

    void deleteStudentById(Long id);

    Student assignStudentToSchool(Long studentId, Long schoolId);
}
