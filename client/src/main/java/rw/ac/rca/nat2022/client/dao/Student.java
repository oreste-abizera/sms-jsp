package rw.ac.rca.nat2022.client.dao;

import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Student {

    private Long id;

    private String names;

    private String gender;


    private String department;

    private String address;

    private String email;


    private Set<School> schools = new HashSet<>();

}
