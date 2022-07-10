package rw.ac.rca.nat2022.server.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.models.Student;
import rw.ac.rca.nat2022.server.services.IStudentService;
import rw.ac.rca.nat2022.server.utils.ApiResponse;
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
    public ApiResponse getAllStudents() {
        return new ApiResponse(HttpStatus.OK, true, "All students fetched", studentService.getAllStudents());
    }

    @GetMapping("/{id}")
    public ApiResponse getStudentById(@PathVariable("id") Long id) {
        return new ApiResponse(HttpStatus.OK, true, "Student fetched", studentService.getStudentById(id));
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody StudentDTO studentDTO) {
        return new ApiResponse(HttpStatus.CREATED, true, "Student saved", studentService.save(studentDTO));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("id") Long id, @RequestBody StudentDTO studentDTO) {
        return new ApiResponse(HttpStatus.OK, true, "Student updated", studentService.update(id, studentDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteStudentById(@PathVariable("id") Long id) {
        studentService.deleteStudentById(id);
        return new ApiResponse(HttpStatus.OK, true, "Student deleted", null);
    }
}
