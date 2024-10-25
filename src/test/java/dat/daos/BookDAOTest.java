package dat.daos;

import dat.config.HibernateConfig;
import dat.dtos.BookDTO;
import dat.entities.Book;
import io.restassured.common.mapper.TypeRef;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import dat.config.ApplicationConfig;
import dat.security.controllers.SecurityController;
import dat.security.daos.SecurityDAO;
import dat.security.exceptions.ValidationException;
import dk.bugelhartmann.UserDTO;
import io.javalin.Javalin;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.*;


import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

class BookDAOTest {

    private static EntityManagerFactory emf;
    private static BookDAO bookDAO;
    private final static SecurityDAO securityDAO = new SecurityDAO(emf);
    private final static SecurityController securityController = SecurityController.getInstance();
    private static Javalin app;
    private static Book[] books;
    private static Book book1, book2;
    private static UserDTO userDTO, adminDTO;
    private static String userToken, adminToken;
    private static final String BASE_URL = "http://localhost:8080/api";

    @BeforeAll
    static void setUpAll() {
        // Set test to true and get the entity manager factory
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        bookDAO = BookDAO.getInstance(emf);

        // Start the server
        //app = ApplicationConfig.startServer(7070);
    }

    @BeforeEach
    void setUp() {
        // Populate the database with hotels and rooms
        System.out.println("Populating database with hotels and rooms");
        books = TestPopulator.populateBooks(emf);
        book1 = books[0];
        book2 = books[1];
        UserDTO[] users = TestPopulator.populateUsers(emf);
        userDTO = users[0];
        adminDTO = users[1];

        try {
            UserDTO verifiedUser = securityDAO.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser(adminDTO.getUsername(), adminDTO.getPassword());
            userToken = "Bearer " + securityController.createToken(verifiedUser);
            adminToken = "Bearer " + securityController.createToken(verifiedAdmin);
        } catch (ValidationException e) {
            throw new RuntimeException(e);
        }

    }

    @AfterEach
    void tearDown() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.createQuery("DELETE FROM Book ").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    static void tearDownAll() {
        ApplicationConfig.stopServer(app);
    }

    @Test
    void Read() {
    }

    @Test
    void readGenre() {
    }

    @Test
    void readTitle() {
    }

    @Test
    void readYear() {
    }

    @Test
    void readAuthor() {
    }

    @Test
    void ReadAll() {
        List<BookDTO> bookDTO =
                given()
                        .when()
                        .header("Authorization", userToken)
                        .get(BASE_URL + "/books")
                        .then()
                        .statusCode(200)
                        .body("size()", is(2))
                        .log().all()
                        .extract()
                        .as(new TypeRef<List<BookDTO>>() {
                        });

        assertThat(bookDTO.size(), is(2));
        assertThat(bookDTO.get(0).getTitle(), is("The Great Gatsby"));
        assertThat(bookDTO.get(1).getTitle(), is("To Kill a Mockingbird"));
    }

    @Test
    void Create() {
    }

    @Test
    void Update() {
    }

    @Test
    void Delete() {
    }
}