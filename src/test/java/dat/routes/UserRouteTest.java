package dat.routes;

import dat.config.ApplicationConfig;
import dat.config.HibernateConfig;
import dat.config.Populator;
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

public class UserRouteTest {
    static Javalin app;
    static String BASE_URL = "http://localhost:8080/api";

    private final static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryForTest();
    private final static SecurityController securityController = SecurityController.getInstance();
    private final static SecurityDAO securityDAO = new SecurityDAO(emf);
    private static UserDTO userDTO, adminDTO;
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
            UserDTO verifiedUser = securityDAO.getVerifiedUser(userDTO.getUsername(), userDTO.getPassword());
            UserDTO verifiedAdmin = securityDAO.getVerifiedUser(adminDTO.getUsername(), adminDTO.getPassword());
            userToken = "Bearer " + securityController.createToken(verifiedUser);
            adminToken = "Bearer " + securityController.createToken(verifiedAdmin);
        }
        catch (ValidationException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterAll
    public static void tearDown() {
        ApplicationConfig.stopServer(app);
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

    @Test
    public void testAuthTest() {
        given()
                .when()
                .get(BASE_URL + "/auth/test/")
                .then()
                .statusCode(200)
                .body("msg", equalTo("Hello from Open"));
    }

    @Test
    public void testRegister() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"test1\", \"password\": \"test1234\"}")
                .when()
                .post(BASE_URL + "/auth/register/")
                .then()
                .statusCode(201)
                .body("username", equalTo("test1"));
    }

    @Test
    public void testLogin() {
        given()
                .contentType(ContentType.JSON)
                .body("{\"username\": \"user1\", \"password\": \"test1234\"}")
                .when()
                .post(BASE_URL + "/auth/login/")
                .then()
                .statusCode(200)
                .body("token", notNullValue());
    }

}
