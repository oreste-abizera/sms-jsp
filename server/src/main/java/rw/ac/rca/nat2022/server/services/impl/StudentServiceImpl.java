package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.Student;
import rw.ac.rca.nat2022.server.repositories.IStudentRepository;
import rw.ac.rca.nat2022.server.services.IStudentService;
import rw.ac.rca.nat2022.server.utils.dtos.StudentDTO;

import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService {
    private final IStudentRepository studentRepository;

    public StudentServiceImpl(IStudentRepository studentRepository) {
        this.studentRepository = studentRepository;
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
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }

}
