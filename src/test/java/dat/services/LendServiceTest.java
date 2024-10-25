package dat.services;

import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.entities.Book;
import dat.entities.LentBook;
import dat.services.LendService;
import dat.daos.LendDAO;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class LendServiceTest {

    /*private EntityManagerFactory emf;
    private LendService lendService;

    @BeforeAll
    public void setup() {
        // Connect to the PostgreSQL database running in Docker
        emf = Persistence.createEntityManagerFactory("test-pu"); // Ensure "test-pu" is configured for PostgreSQL
        lendService = LendService.getInstance(emf);

        // Set up a user and book for lending
        UserDTO user = new UserDTO("john.doe", "John Doe");
        BookDTO book = new BookDTO(1, "Sample Book", 2020, "Author", Book.Genre.FICTION);

        // Lend the book to the user
        LentBookDTO lentBook = new LentBookDTO(1, user, book, LocalDateTime.now(), LocalDateTime.now().plusDays(28));
        lendService.lendBook(user, 1L); // Assuming book ID 1 is valid
    }

    @AfterAll
    public void tearDown() {
        // Optionally, clear the database or close the entity manager factory
        emf.close();
    }

    @Test
    public void testLendBook() {
        UserDTO user = new UserDTO("jane.doe", "Jane Doe");
        BookDTO book = new BookDTO(2, "Another Book", 2021, "Another Author", Book.Genre.NON_FICTION); // Create a new book DTO

        LentBookDTO lentBook = lendService.lendBook(user, 2L); // Lend another book

        assertThat(lentBook, is(notNullValue()));
        assertThat(lentBook.getBook().getId(), is(2L)); // Assuming the book DTO is included in the lent book DTO
        assertThat(lentBook.getUser().getUsername(), is("jane.doe"));
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
        long idToDelete = 1; // Assuming ID 1 exists
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
    }*/
}
