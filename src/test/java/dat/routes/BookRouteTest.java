package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populator;
import dat.daos.BookDAO;
import dat.security.controllers.SecurityController;
import dat.security.daos.SecurityDAO;
import dat.security.exceptions.ValidationException;
import dk.bugelhartmann.UserDTO;
import io.javalin.Javalin;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

class BookRouteTest {

    /*static Javalin app;
    static String BASE_URL = "http://localhost:8080/api";

    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private final static SecurityController securityController = SecurityController.getInstance();
    private final static SecurityDAO securityDAO = new SecurityDAO(emf);
    private final static BookDAO bookDAO = BookDAO.getInstance(emf);
    private static String userToken, adminToken;

    @BeforeAll
    public static void setup() {
        app = ApplicationConfig.startServer(8080);
    }

    @BeforeEach
    public void setUp() {
        //Fill up the database
        Populator.main(null);

        try {
            UserDTO verifiedUser = securityDAO.getVerifiedUser("user1", "test1234");
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser("admin", "admin1234");
            userToken = "Bearer " + securityController.createToken(verifiedUser);
            adminToken = "Bearer " + securityController.createToken(verifiedAdmin);
        }
        catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void cleanUp() {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM LentBook").executeUpdate();
            em.createQuery("DELETE FROM Book").executeUpdate();
            em.createQuery("DELETE FROM User").executeUpdate();
            em.createQuery("DELETE FROM Role").executeUpdate();
            em.getTransaction().commit();
        }
    }

    @AfterAll
    public static void tearDown() {
        ApplicationConfig.stopServer(app);
    }

    @Test
    public void testGetAllBooks() {
        given()
                .when()
                .get(BASE_URL + "/books/")
                .then()
                .statusCode(200)
                .body("size()", equalTo(10)); // Assuming there are 10 books in the database
    }

    @Test
    public void testGetBookById() {
        given()
                .when()
                .get(BASE_URL + "/books/1")
                .then()
                .statusCode(200)
                .body("title", notNullValue()); // Assuming book with ID 1 is "The Great Gatsby"
    }

    @Test
    public void testCreateBook() {
        String json = """
                {
                  "title": "Test Book",
                  "year": 1900,
                  "author": "J.R.R. Tolkien",
                  "genre": "FICTION"
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", adminToken)
                .body(json)
                .when()
                .post(BASE_URL + "/books")
                .then()
                .statusCode(201)
                .body("title", equalTo("Test Book"));
    }

    @Test
    public void testUpdateBook() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", adminToken)
                .body("{\"title\": \"Updated Book\", \"year\": 2023, \"author\": \"Updated Author\", \"genre\": \"FICTION\"}")
                .when()
                .put(BASE_URL + "/books/1")
                .then()
                .statusCode(200)
                .body("title", equalTo("Updated Book"));
    }

    @Test
    public void testDeleteBook() {
        given()
                .when()
                .header("Authorization", adminToken)
                .delete(BASE_URL + "/books/1")
                .then()
                .statusCode(200);
    }*/

}