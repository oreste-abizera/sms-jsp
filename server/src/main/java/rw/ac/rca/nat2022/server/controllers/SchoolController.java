package rw.ac.rca.nat2022.server.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.models.School;
import rw.ac.rca.nat2022.server.services.ISchoolService;
import rw.ac.rca.nat2022.server.utils.dtos.SchoolDTO;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
public class SchoolController {
    private final ISchoolService schoolService;

    public SchoolController(ISchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @GetMapping("")
    public ResponseEntity<List<School>> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools());
    }

    @PostMapping("")
    public ResponseEntity<School> save(@RequestBody SchoolDTO schoolDTO) {
        return ResponseEntity.ok(schoolService.save(schoolDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<School> getSchoolById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(schoolService.getSchoolById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<School> update(@PathVariable("id") Long id,@RequestBody SchoolDTO schoolDTO) {
        return ResponseEntity.ok(schoolService.update(id, schoolDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSchoolById(@PathVariable("id") Long id) {
        schoolService.deleteSchoolById(id);
        return ResponseEntity.ok().build();
    }
}
