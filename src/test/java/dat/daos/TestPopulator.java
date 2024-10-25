package dat.daos;

import dat.entities.Book;
import dat.security.entities.Role;
import dat.security.entities.User;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;

public class TestPopulator {

    public static UserDTO[] populateUsers(EntityManagerFactory emf) {
        User user, admin;
        Role userRole, adminRole;

        user = new User("usertest", "user123");
        admin = new User("admintest", "admin123");
        userRole = new Role("USER");
        adminRole = new Role("ADMIN");
        user.addRole(userRole);
        admin.addRole(adminRole);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(admin);
            em.getTransaction().commit();
        }
        UserDTO userDTO = new UserDTO(user.getUsername(), "user123");
        UserDTO adminDTO = new UserDTO(admin.getUsername(), "admin123");
        return new UserDTO[]{userDTO, adminDTO};
    }

    public static Book[] populateBooks(EntityManagerFactory emf) {
        Book book1, book2;
        book1 = new Book("The Great Gatsby", 1925, "Francis Scott Fitzgerald", true, LocalDateTime.now(), Book.Genre.ROMANCE);
        book2 = new Book("To Kill a Mockingbird", 1960, "Harper Lee", false, LocalDateTime.now(), Book.Genre.CRIME);

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.persist(book1);
            em.persist(book2);
            em.getTransaction().commit();
        }
        return new Book[]{book1, book2};
    }
}
