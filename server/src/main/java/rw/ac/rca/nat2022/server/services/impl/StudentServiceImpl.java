package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.School;
import rw.ac.rca.nat2022.server.models.Student;
import rw.ac.rca.nat2022.server.repositories.ISchoolRepository;
import rw.ac.rca.nat2022.server.repositories.IStudentRepository;
import rw.ac.rca.nat2022.server.services.IStudentService;
import rw.ac.rca.nat2022.server.utils.dtos.StudentDTO;

import java.util.List;
import java.util.Set;

@Service
public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository studentRepository;
    private final ISchoolRepository schoolRepository;

    public StudentServiceImpl(IStudentRepository studentRepository, ISchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student save(StudentDTO studentDTO) {
        return studentRepository.save(new Student(studentDTO));
    }

    @Override
    public Student update(Long id,StudentDTO studentDTO) {
        Student student = studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student not found")
        );
        student.setNames(studentDTO.getNames());
        student.setGender(studentDTO.getGender());
        student.setDepartment(studentDTO.getDepartment());
        student.setEmail(studentDTO.getEmail());
        student.setAddress(studentDTO.getAddress());
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Student with id " + id + " does not exist")
        );
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

    @Override
    public Student assignStudentToSchool(Long studentId, Long schoolId) {
        Student student = studentRepository.findById(studentId).orElseThrow(
                () -> new IllegalArgumentException("Student not found")
        );
        School school = schoolRepository.findById(schoolId).orElseThrow(
                () -> new IllegalArgumentException("School not found")
        );
        Set<School> schools = student.getSchools();
        schools.add(school);
        student.setSchools(schools);
        return studentRepository.save(student);
    }

}
