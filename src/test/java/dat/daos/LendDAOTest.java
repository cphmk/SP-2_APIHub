package dat.daos;

import dat.config.HibernateConfig;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LendDAOTest {

    private static EntityManagerFactory emf;
    private static LendDAO lendDAO;

    @BeforeAll
    static void setUpAll() {
        // Set test to true and get the entity manager factory
        HibernateConfig.setTest(true);
        emf = HibernateConfig.getEntityManagerFactory();
        lendDAO = LendDAO.getInstance(emf);

        // Start the server
        //app = ApplicationConfig.startServer(7070);
    }

    @Test
    void lendBook() {
    }

    @Test
    void read() {
    }

    @Test
    void readUserLends() {
    }

    @Test
    void readAll() {
    }

    @Test
    void create() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void validatePrimaryKey() {
    }
}