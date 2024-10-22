package dat.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class EmployeeDTO {
    private Integer id;
    private String employeeName;
    private Integer employeeAge;
    private String employeeGender;
    private String employeeEmail;
    private Set<LibraryDTO> libraries = new HashSet<>();

    public EmployeeDTO(String employeeName, Integer employeeAge, String employeeGender, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeGender = employeeGender;
        this.employeeEmail = employeeEmail;
    }
}
