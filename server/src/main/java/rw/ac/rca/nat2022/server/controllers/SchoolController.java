package rw.ac.rca.nat2022.server.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.nat2022.server.models.School;
import rw.ac.rca.nat2022.server.services.ISchoolService;
import rw.ac.rca.nat2022.server.utils.ApiResponse;
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
    public ApiResponse getAllSchools() {
        return new ApiResponse(HttpStatus.OK, true, "All schools fetched", schoolService.getAllSchools());
    }

    @PostMapping("")
    public ApiResponse save(@RequestBody SchoolDTO schoolDTO) {
        return new ApiResponse(HttpStatus.CREATED, true, "School saved", schoolService.save(schoolDTO));
    }

    @GetMapping("/{id}")
    public ApiResponse getSchoolById(@PathVariable("id") Long id) {
        return new ApiResponse(HttpStatus.OK, true, "School fetched", schoolService.getSchoolById(id));
    }

    @PutMapping("/{id}")
    public ApiResponse update(@PathVariable("id") Long id,@RequestBody SchoolDTO schoolDTO) {
        return new ApiResponse(HttpStatus.OK, true, "School updated", schoolService.update(id, schoolDTO));
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteSchoolById(@PathVariable("id") Long id) {
        schoolService.deleteSchoolById(id);
        return new ApiResponse(HttpStatus.OK, true, "School deleted", null);
    }
}
