package dat.dtos;

import dat.entities.Employee;
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
    private Employee.EmployeeGender employeeGender;
    private String employeeEmail;
    private Set<LibraryDTO> libraries = new HashSet<>();

    public EmployeeDTO(String employeeName, Integer employeeAge, Employee.EmployeeGender employeeGender, String employeeEmail) {
        this.employeeName = employeeName;
        this.employeeAge = employeeAge;
        this.employeeGender = employeeGender;
        this.employeeEmail = employeeEmail;
    }

    public EmployeeDTO(Employee employee) {
        this.employeeName = employee.getEmployeeName();
        this.employeeAge = employee.getEmployeeAge();
        this.employeeGender = employee.getEmployeeGender();
        this.employeeEmail = employee.getEmployeeEmail();
    }
}
