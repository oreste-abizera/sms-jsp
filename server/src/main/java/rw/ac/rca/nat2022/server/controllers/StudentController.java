package rw.ac.rca.nat2022.server.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.models.Student;
import rw.ac.rca.nat2022.server.services.IStudentService;
import rw.ac.rca.nat2022.server.utils.dtos.StudentDTO;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    private final IStudentService studentService;

    public StudentController(IStudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("")
    public ResponseEntity<List<Student>> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    @PostMapping("")
    public ResponseEntity<Student> save(@RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.save(studentDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        return ResponseEntity.ok(studentService.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok().build();
    }
}
