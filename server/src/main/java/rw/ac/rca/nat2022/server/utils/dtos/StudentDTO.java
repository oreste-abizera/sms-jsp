package rw.ac.rca.nat2022.server.utils.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    private String names;
    private String gender;
    private String email;
    private String department;
    private String address;
}
