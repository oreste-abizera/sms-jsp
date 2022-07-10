package rw.ac.rca.nat2022.server.services.impl;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.School;
import rw.ac.rca.nat2022.server.repositories.ISchoolRepository;
import rw.ac.rca.nat2022.server.services.ISchoolService;
import rw.ac.rca.nat2022.server.utils.dtos.SchoolDTO;

import java.util.List;

@Service
public class SchoolServiceImpl implements ISchoolService {

    private final ISchoolRepository schoolRepository;

    public SchoolServiceImpl(ISchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School save(SchoolDTO school) {
        return schoolRepository.save(new School(school));
    }

    @Override
    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("School with id " + id + " does not exist")
        );
    }

    @Override
    public School update(Long id, SchoolDTO schoolDTO){
        School school = schoolRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("School not found")
        );
        school.setName(schoolDTO.getName());
        school.setLocation(schoolDTO.getLocation());
        return schoolRepository.save(school);
    }

    @Override
    public void deleteSchoolById(Long id) {
        schoolRepository.deleteById(id);
    }
}
