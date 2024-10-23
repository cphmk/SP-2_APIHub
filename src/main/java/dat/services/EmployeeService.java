package dat.services;

import dat.daos.BookDAO;
import dat.daos.EmployeeDAO;
import dat.dtos.BookDTO;
import dat.dtos.EmployeeDTO;
import dat.entities.Employee;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class EmployeeService {



    private EmployeeDAO employeeDAO;

    public EmployeeService(EntityManagerFactory emf) {
        this.employeeDAO = employeeDAO.getInstance(emf);
    }

    public EmployeeDTO read(int id) {
        return employeeDAO.read(id);
    }

    public List<BookDTO> readAll() {
        return employeeDAO.readAll();
    }

    public BookDTO create(BookDTO hotelDTO) {
        return BookDAO.create(hotelDTO);
    }

    public BookDTO update(int id, BookDTO hotelDTO) {
        return employeeDAO.update(id, hotelDTO);
    }

    public void delete(int id) {
        employeeDAO.delete(id);
    }

    public boolean validatePrimaryKey(Integer id) {
        return employeeDAO.validatePrimaryKey(id);
    }

    public boolean validateEmployeeDTO(EmployeeDTO employeeDTO) {
        return employeeDTO.getEmployeeName() != null && !employeeDTO.getEmployeeName().isEmpty() &&  // Checking if name is not null or empty
                employeeDTO.getEmployeeAge() != null &&  //  checking if age is not null
                employeeDTO.getEmployeeEmail() != null &&   // checking if Email is not null
                employeeDTO.getEmployeeGender() != null &&   // checking if gender is not null
                employeeDTO.getLibraries() != null && !employeeDTO.getLibraries().isEmpty(); // Checking if Libraries is not null or empty

    }
}
