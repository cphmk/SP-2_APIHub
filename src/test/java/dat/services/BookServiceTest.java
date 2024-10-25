package dat.services;

import dat.dtos.BookDTO;
import dat.entities.Book;
import dat.services.BookService;
import dat.daos.BookDAO;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BookServiceTest {

    private EntityManagerFactory emf;
    private BookService bookService;
    private BookDAO bookDAO;

    @BeforeAll
    public void setup() {
        // Connect to the PostgreSQL database running in Docker
        emf = Persistence.createEntityManagerFactory("test-pu"); // Ensure "test-pu" is configured for PostgreSQL
        bookDAO = BookDAO.getInstance(emf);
        bookService = BookService.getInstance(emf);

        // Add initial data to the database
        bookService.create(new BookDTO("Sample Title", 2020, "Author", Book.Genre.FICTION));
        bookService.create(new BookDTO("Another Title", 2021, "Another Author", Book.Genre.NON_FICTION));
    }

    @AfterAll
    public void tearDown() {
        // Optionally, clear the database or close the entity manager factory
        emf.close();
    }

    @Test
    public void testRead() {
        BookDTO book = bookService.read(1);

        assertThat(book, is(notNullValue()));
        assertThat(book.getTitle(), is("Sample Title"));
    }

    @Test
    public void testReadGenre() {
        List<BookDTO> books = bookService.readGenre(Book.Genre.FICTION);

        assertThat(books, hasSize(1));
        assertThat(books.get(0).getTitle(), is("Sample Title"));
    }

    @Test
    public void testCreate() {
        BookDTO newBook = new BookDTO("New Book", 2023, "New Author", Book.Genre.FICTION);
        BookDTO createdBook = bookService.create(newBook);

        assertThat(createdBook.getTitle(), is("New Book"));
        assertThat(bookService.readAll(), hasSize(3)); // Confirms addition to database
    }

    @Test
    public void testUpdate() {
        BookDTO updatedBook = new BookDTO("Updated Title", 2022, "Updated Author", Book.Genre.FICTION);
        bookService.update(1, updatedBook);

        BookDTO book = bookService.read(1);
        assertThat(book.getTitle(), is("Updated Title"));
    }

    @Test
    public void testDelete() {
        int idToDelete = 2; // Assuming ID 2 exists
        bookService.delete(idToDelete);

        BookDTO book = bookService.read(idToDelete);
        assertThat(book, is(nullValue()));
    }

    @Test
    public void testValidatePrimaryKey_Valid() {
        boolean isValid = bookService.validatePrimaryKey(1);

        assertThat(isValid, is(true));
    }

    @Test
    public void testValidatePrimaryKey_Invalid() {
        boolean isValid = bookService.validatePrimaryKey(999); // Assume 999 does not exist

        assertThat(isValid, is(false));
    }

    @Test
    public void testValidateBookDTO_Valid() {
        BookDTO validBook = new BookDTO("Valid Title", 2023, "Valid Author", Book.Genre.FICTION);

        boolean isValid = bookService.validateBookDTO(validBook);

        assertThat(isValid, is(true));
    }

    @Test
    public void testValidateBookDTO_Invalid() {
        BookDTO invalidBook = new BookDTO("", null, "Author", null);

        boolean isValid = bookService.validateBookDTO(invalidBook);

        assertThat(isValid, is(false));
    }
}
