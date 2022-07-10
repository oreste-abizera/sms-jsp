package rw.ac.rca.nat2022.server.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rw.ac.rca.nat2022.server.models.School;

@Repository
public interface ISchoolRepository extends JpaRepository<School, Long> {

}
