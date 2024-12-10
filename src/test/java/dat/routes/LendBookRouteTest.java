package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populator;
import dat.daos.LendDAO;
import dat.security.controllers.SecurityController;
import dat.security.daos.SecurityDAO;
import dat.security.exceptions.ValidationException;
import dk.bugelhartmann.UserDTO;
import io.javalin.Javalin;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

class LendBookRouteTest {

    static Javalin app;
    static String BASE_URL = "http://localhost:8080/api";

    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private final static SecurityController securityController = SecurityController.getInstance();
    private final static SecurityDAO securityDAO = new SecurityDAO(emf);
    private final static LendDAO lendDAO = LendDAO.getInstance(emf);
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
    public void testGetAllLentBooks() {
        given()
                .when()
                .header("Authorization", adminToken)
                .get(BASE_URL + "/lendbooks/")
                .then()
                .statusCode(200)
                .body("size()", equalTo(8)); // Assuming there are 10 lent books in the database
    }

    @Test
    public void testGetLentBookById() {
        given()
                .when()
                .header("Authorization", adminToken)
                .get(BASE_URL + "/lendbooks/1")
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    public void testCreateLentBook() {
        given()
                .header("Authorization", userToken)
                .when()
                .post(BASE_URL + "/lendbooks/1")
                .then()
                .statusCode(201)
                .body("id", notNullValue());
    }

    @Test
    public void testUpdateLentBook() {
        String json = """
                {
                  "lentDate": [
                    2024,
                    10,
                    28,
                    15,
                    7,
                    28,
                    989201000
                  ]
                }
                """;

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", adminToken)
                .body(json)
                .when()
                .put(BASE_URL + "/lendbooks/1")
                .then()
                .statusCode(200)
                .body("id", equalTo("2023-03-01"));
    }

    @Test
    public void testDeleteLentBook() {
        given()
                .when()
                .header("Authorization", adminToken)
                .delete(BASE_URL + "/lendbooks/1")
                .then()
                .statusCode(204);
    }

}
