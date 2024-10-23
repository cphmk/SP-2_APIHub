package dat.daos;

import jakarta.persistence.EntityManagerFactory;

public class GenreDAO {

    private static GenreDAO instance;
    private static EntityManagerFactory emf;

    public static GenreDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new GenreDAO();
        }
        return instance;
    }

}
