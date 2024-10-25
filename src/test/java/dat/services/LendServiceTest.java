package dat.services;

import dat.config.HibernateConfig;
import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.entities.Book;
import dat.entities.LentBook;
import dat.security.entities.User;
import dat.services.LendService;
import dat.daos.LendDAO;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LendServiceTest {

    private EntityManagerFactory emf;
    private LendService lendService;

    @BeforeAll
    public void setup() {
        // Connect to the PostgreSQL database running in Docker
        emf = HibernateConfig.getEntityManagerFactory();
        lendService = LendService.getInstance(emf);

        // Set up a user and book for lending
        UserDTO userDTO = new UserDTO("john.doe", "John Doe");
        BookDTO bookDTO = new BookDTO(1, "Sample Book", 2020, "Author", Book.Genre.FICTION);

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            User user = new User(userDTO.getUsername(), userDTO.getUsername());
            Book book = new Book(bookDTO.getTitle(), bookDTO.getYear(), bookDTO.getAuthor(), bookDTO.getGenre());
            em.persist(user);
            em.persist(book);
            em.getTransaction().commit();
        }

        // Lend the book to the user
        LentBookDTO lentBook = new LentBookDTO(1, userDTO, bookDTO, LocalDateTime.now(), LocalDateTime.now().plusDays(28));
        lendService.lendBook(userDTO, 1L); // Assuming book ID 1 is valid
    }

    @AfterAll
    public void tearDown() {
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
    public void testLendBook() {
        UserDTO userDTO = new UserDTO("jane.doe", "Jane Doe");
        BookDTO bookDTO = new BookDTO(2, "Another Book", 2021, "Another Author", Book.Genre.NON_FICTION); // Create a new book DTO

        User user = null;
        Book book = null;

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            user = new User(userDTO.getUsername(), userDTO.getUsername());
            book = new Book(bookDTO.getTitle(), bookDTO.getYear(), bookDTO.getAuthor(), bookDTO.getGenre());
            em.persist(user);
            em.persist(book);
            em.getTransaction().commit();
        }
        LentBookDTO lentBookDTO = lendService.lendBook(userDTO, 2); // Lend another book


        assertThat(lentBookDTO, is(notNullValue()));
        assertThat(lentBookDTO.getBook().getId(), is(2)); // Assuming the book DTO is included in the lent book DTO
        assertThat(lentBookDTO.getUser().getUsername(), is("jane.doe"));
    }

    @Test
    public void testReadUserLends() {
        UserDTO user = new UserDTO("john.doe", "John Doe");
        List<LentBookDTO> userLends = lendService.readUserLends(user);

        assertThat(userLends, hasSize(1));
        assertThat(userLends.get(0).getUser().getUsername(), is("john.doe"));
    }

    @Test
    public void testReadAllLends() {
        List<LentBookDTO> allLends = lendService.readAllLends();

        assertThat(allLends, hasSize(1)); // Only one lend should exist based on setup
    }

    @Test
    public void testDelete() {
        long idToDelete = 2; // Assuming ID 1 exists
        lendService.delete(idToDelete);

        List<LentBookDTO> allLends = lendService.readAllLends();
        assertThat(allLends, not(hasItem(hasProperty("id", is(idToDelete)))));
    }

    @Test
    public void testUpdateLentBook_Admin() {
        UserDTO adminUser = new UserDTO("admin", "Admin User");
        BookDTO book = new BookDTO(1, "Sample Book", 2020, "Author", Book.Genre.FICTION); // Create a book DTO
        LentBookDTO updatedLentBook = new LentBookDTO(2, adminUser, book, LocalDateTime.now(), LocalDateTime.now().plusDays(28));

        LentBookDTO result = lendService.updateLentBook(adminUser, 1L, updatedLentBook, "admin");

        assertThat(result, is(notNullValue()));
        assertThat(result.getBook().getId(), is(1L));
    }

    @Test
    public void testUpdateLentBook_User() {
        UserDTO user = new UserDTO("john.doe", "John Doe");
        BookDTO book = new BookDTO(1, "Sample Book", 2020, "Author", Book.Genre.FICTION); // Create a book DTO
        LentBookDTO updatedLentBook = new LentBookDTO(3, user, book, LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(28));

        LentBookDTO result = lendService.updateLentBook(user, 1L, updatedLentBook, "user");

        assertThat(result, is(notNullValue()));
        assertThat(result.getBook().getId(), is(1L));
    }

    @Test
    public void testUpdateLentBook_UserAfterLentDate() {
        UserDTO user = new UserDTO("john.doe", "John Doe");
        BookDTO book = new BookDTO(1, "Sample Book", 2020, "Author", Book.Genre.FICTION); // Create a book DTO
        LentBookDTO updatedLentBook = new LentBookDTO(4, user, book, LocalDateTime.now().minusDays(1), LocalDateTime.now().plusDays(28)); // Set a past date

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            lendService.updateLentBook(user, 1L, updatedLentBook, "user");
        });

        assertThat(exception.getMessage(), is("Loan cannot be updated after the lent date has been reached."));
    }
}*/
