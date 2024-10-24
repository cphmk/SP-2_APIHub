package dat.daos;

import dat.config.HibernateConfig;
import dat.dtos.BookDTO;
import dat.entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BookDAOTest {

    private static EntityManagerFactory emf;
    private static BookDAO bookDAO;

    @BeforeAll
    static void setUpAll() {
        // Set test to true and get the entity manager factory
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        bookDAO = BookDAO.getInstance(emf);

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
    void read() {
        BookDTO bookDTO = bookDAO.create(new BookDTO("Test Book", 2023, "Test Author", Book.Genre.FICTION));
        BookDTO readBook = bookDAO.read(bookDTO.getId());
        assertNotNull(readBook);
        assertEquals("Test Book", readBook.getTitle());
    }

    @Test
    void readAll() {
        bookDAO.create(new BookDTO("Test Book 1", 2023, "Test Author 1", Book.Genre.FICTION));
        bookDAO.create(new BookDTO("Test Book 2", 2023, "Test Author 2", Book.Genre.FICTION));
        List<BookDTO> books = bookDAO.readAll();
        assertFalse(books.isEmpty());
        assertTrue(books.size() >= 2);
    }

    @Test
    void create() {
        BookDTO bookDTO = new BookDTO("New Book", 2023, "New Author", Book.Genre.FICTION);
        BookDTO createdBook = bookDAO.create(bookDTO);
        assertNotNull(createdBook);
        assertEquals("New Book", createdBook.getTitle());
    }

    @Test
    void update() {
        BookDTO bookDTO = bookDAO.create(new BookDTO("Old Title", 2023, "Old Author", Book.Genre.FICTION));
        bookDTO.setTitle("Updated Title");
        BookDTO updatedBook = bookDAO.update(bookDTO.getId(), bookDTO);
        assertNotNull(updatedBook);
        assertEquals("Updated Title", updatedBook.getTitle());
    }

    @Test
    void delete() {
        BookDTO bookDTO = bookDAO.create(new BookDTO("Delete Book", 2023, "Delete Author", Book.Genre.FICTION));
        bookDAO.delete(bookDTO.getId());
        assertThrows(EntityNotFoundException.class, () -> {
            bookDAO.read(bookDTO.getId());
        });
    }
}