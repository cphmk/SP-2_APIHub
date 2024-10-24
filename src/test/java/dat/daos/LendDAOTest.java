package dat.daos;

import dat.config.HibernateConfig;
import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.entities.Book;
import dat.security.daos.SecurityDAO;
import dat.security.entities.User;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LendDAOTest {

    private static EntityManagerFactory emf;
    private static LendDAO lendDAO;
    private static BookDAO bookDAO;
    private static SecurityDAO securityDAO;

    private User user;
    private BookDTO bookDTO1, bookDTO2;

    @BeforeAll
    static void setUpAll() {
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        lendDAO = LendDAO.getInstance(emf);
        bookDAO = BookDAO.getInstance(emf);
        securityDAO = new SecurityDAO(emf);
    }

    @BeforeEach
    void setUpEach() {
        user = securityDAO.createUser("testuser", "password");
        bookDTO1 = bookDAO.create(new BookDTO("Test Book 1", 2023, "Test Author 1", Book.Genre.FICTION));
        bookDTO2 = bookDAO.create(new BookDTO("Test Book 2", 2023, "Test Author 2", Book.Genre.FICTION));
    }

    @AfterEach
    void tearDown() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM LentBook").executeUpdate();
            em.createQuery("DELETE FROM Book").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @Test
    void lendBook() {
        LentBookDTO lentBookDTO = lendDAO.lendBook(new UserDTO(user.getUsername(), user.getPassword()), Long.valueOf(bookDTO1.getId()));
        assertNotNull(lentBookDTO);
        assertEquals("testuser", lentBookDTO.getUser().getUsername());
        assertEquals("Test Book 1", lentBookDTO.getBook().getTitle());
    }

    @Test
    void readUserLends() {
        lendDAO.lendBook(new UserDTO(user.getUsername(), user.getPassword()), Long.valueOf(bookDTO1.getId()));
        lendDAO.lendBook(new UserDTO(user.getUsername(), user.getPassword()), Long.valueOf(bookDTO2.getId()));
        List<LentBookDTO> lentBooks = lendDAO.readUserLends(new UserDTO(user.getUsername(), user.getPassword()));
        assertFalse(lentBooks.isEmpty());
        assertEquals(2, lentBooks.size());
    }

    @Test
    void update() {
        LentBookDTO lentBookDTO = lendDAO.lendBook(new UserDTO(user.getUsername(), user.getPassword()), Long.valueOf(bookDTO1.getId()));
        lentBookDTO.setReturnDate(LocalDateTime.now());
        LentBookDTO updatedLentBook = lendDAO.update(Long.valueOf(lentBookDTO.getId()), lentBookDTO);
        assertNotNull(updatedLentBook);
        assertNotNull(updatedLentBook.getReturnDate());
    }

    @Test
    void delete() {
        LentBookDTO lentBookDTO = lendDAO.lendBook(new UserDTO(user.getUsername(),user.getPassword()), Long.valueOf(bookDTO1.getId()));
        lendDAO.delete(Long.valueOf(lentBookDTO.getId()));
        LentBookDTO deletedLentBook = lendDAO.read(Long.valueOf(lentBookDTO.getId()));
        assertNull(deletedLentBook);
    }
}