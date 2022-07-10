package rw.ac.rca.nat2022.server.services;

import org.springframework.stereotype.Service;
import rw.ac.rca.nat2022.server.models.School;
import rw.ac.rca.nat2022.server.utils.dtos.SchoolDTO;

import java.util.List;

public interface ISchoolService {

    List<School> getAllSchools();

    School save(SchoolDTO schoolDTO);

    School getSchoolById(Long id);

    School update(Long id,SchoolDTO schoolDTO);

    void deleteSchoolById(Long id);
}
