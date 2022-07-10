package rw.ac.rca.nat2022.client.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class School {

    private Long id;

    private String name;

    private String location;

    private Set<Student> students = new HashSet<>();
}
