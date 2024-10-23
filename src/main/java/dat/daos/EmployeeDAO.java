package dat.daos;

import jakarta.persistence.EntityManagerFactory;

public class EmployeeDAO {

    private static EmployeeDAO instance;
    private static EntityManagerFactory emf;

    public static EmployeeDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new EmployeeDAO();
        }
        return instance;
    }

}
