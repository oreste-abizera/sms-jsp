package rw.ac.rca.nat2022.server.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.ac.rca.nat2022.server.utils.dtos.StudentDTO;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "names", nullable = false)
    private String names;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "address", nullable = false)
    private String address;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "Student_school",
            joinColumns = { @JoinColumn(name = "student_id") },
            inverseJoinColumns = { @JoinColumn(name = "school_id") }
    )
    Set<School> schools = new HashSet<>();

    public Student(StudentDTO studentDTO){
        this.names = studentDTO.getNames();
        this.gender = studentDTO.getGender();
        this.email = studentDTO.getEmail();
        this.department = studentDTO.getDepartment();
        this.address = studentDTO.getAddress();
    }
}
