package dat.daos;

import jakarta.persistence.EntityManagerFactory;

public class LibraryDAO {

    private static LibraryDAO instance;
    private static EntityManagerFactory emf;

    public static LibraryDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LibraryDAO();
        }
        return instance;
    }

}
