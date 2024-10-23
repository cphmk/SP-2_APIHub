package dat.services;

import dat.daos.BookDAO;
import dat.daos.LibraryDAO;
import dat.dtos.BookDTO;
import dat.dtos.LibraryDTO;
import dat.entities.Library;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class LibraryService {



    private LibraryDAO libraryDAO;

    public LibraryService(EntityManagerFactory emf) {
        this.libraryDAO = libraryDAO.getInstance(emf);
    }

    public LibraryDTO read(int id) {
        return libraryDAO.read(id);
    }

    public List<BookDTO> readAll() {
        return libraryDAO.readAll();
    }

    public BookDTO create(BookDTO hotelDTO) {
        return BookDAO.create(hotelDTO);
    }

    public BookDTO update(int id, BookDTO hotelDTO) {
        return libraryDAO.update(id, hotelDTO);
    }

    public void delete(int id) {
        libraryDAO.delete(id);
    }

    public boolean validatePrimaryKey(Integer id) {
        return libraryDAO.validatePrimaryKey(id);
    }

    public boolean validateLibraryDTO(LibraryDTO libraryDTO) {
        return libraryDTO.getLibraryName() != null && !libraryDTO.getLibraryName().isEmpty() &&  // Checking if library name is not null or empty
                libraryDTO.getLibraryAddress() != null && !libraryDTO.getLibraryAddress().isEmpty() && //  checking if library address is not null or empty
                libraryDTO.getBooks() != null &&   // checking if books is not null
                libraryDTO.getEmployees() != null && !libraryDTO.getEmployees().isEmpty(); // checking if employees is not null or empty
    }
}
