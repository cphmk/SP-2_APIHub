package dat.services;

import dat.config.HibernateConfig;
import dat.daos.LendDAO;
import dat.dtos.LentBookDTO;
import dat.entities.LentBook;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.List;

public class LendService {

    private static LendDAO dao;
    private static LendService instance;

    public static LendService getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new LendService();
            dao = LendDAO.getInstance(_emf);
        }
        return instance;
    }

    public LentBookDTO lendBook(UserDTO userDTO, long bookId) {
        return dao.lendBook(userDTO, bookId);
    }

    public List<LentBookDTO> readUserLends(UserDTO userDTO) {
        return dao.readUserLends(userDTO);
    }

    public List<LentBookDTO> readAllLends() {
        return dao.readAll();
    }

    public void delete(long id) {
        dao.delete(id);
    }

    public LentBookDTO updateLentBook(UserDTO userDTO, Long bookId, LentBookDTO lentBookDTO, String userRole) {
        // Check if the user is an admin or normal user
        if ("admin".equalsIgnoreCase(userRole)) {
            return dao.update(bookId, lentBookDTO);  // Admins can update regardless of date
        }

        // For normal users, check that today’s date is before the lentDate
        LentBook lentBook = dao.getLentBook(userDTO, bookId);
        if (LocalDateTime.now().isAfter(lentBook.getLentDate())) {
            throw new IllegalArgumentException("Loan cannot be updated after the lent date has been reached.");
        }

        return dao.updateOwnLoan(userDTO, bookId, lentBookDTO); // Normal users can update if before lentDate
    }


}
