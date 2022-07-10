package rw.ac.rca.nat2022.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.nat2022.server.utils.dtos.SchoolDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Schools")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class School {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @ManyToMany(mappedBy = "schools")
    private Set<Student> students = new HashSet<>();

    public School(SchoolDTO schoolDTO) {
        this.name = schoolDTO.getName();
        this.location = schoolDTO.getLocation();
    }
}
