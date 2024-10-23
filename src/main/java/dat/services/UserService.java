package dat.services;

import dat.daos.BookDAO;
import dat.daos.LibraryDAO;
import dat.daos.UserDAO;
import dat.dtos.BookDTO;
import dat.dtos.LibraryDTO;
import dat.dtos.UserDTO;
import dat.entities.Library;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class UserService {



    private UserDAO userDAO;

    public UserService(EntityManagerFactory emf) {
        this.userDAO = userDAO.getInstance(emf);
    }

    public LibraryDTO read(int id) {
        return userDAO.read(id);
    }

    public List<UserDTO> readAll() {
        return userDAO.readAll();
    }

    public BookDTO create(BookDTO hotelDTO) {
        return BookDAO.create(hotelDTO);
    }

    public BookDTO update(int id, BookDTO hotelDTO) {
        return userDAO.update(id, hotelDTO);
    }

    public void delete(int id) {
        userDAO.delete(id);
    }

    public boolean validatePrimaryKey(Integer id) {
        return userDAO.validatePrimaryKey(id);
    }

    public boolean validateUserDTO(UserDTO userDTO) {
        return  userDTO.getUserName() != null && !userDTO.getUserName().isEmpty() &&  // Checking if username is not null or empty
                userDTO.getUserPassword() != null &&  //  checking if password is not null or empty
                userDTO.getBooks() != null &&   // checking if books is not null
                userDTO.getUserRole() != null && !userDTO.getUserRole().isEmpty(); // checking if roles is not null or empty
    }
}

