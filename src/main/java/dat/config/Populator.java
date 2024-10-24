package dat.config;

import dat.entities.Book;
import dat.entities.LentBook;
import dat.security.daos.SecurityDAO;
import dat.security.entities.Role;
import dat.security.entities.User;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();
        SecurityDAO securityDAO = new SecurityDAO(emf);

        Set<Book> books = getBooks();
        List<User> users = getUsers();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            books.forEach(em::persist);
            users.forEach(user -> {
                securityDAO.createUser(user.getUsername(), user.getPassword());
            });
            securityDAO.addRole(new UserDTO(users.get(2).getUsername(), users.get(2).getPassword()), "ADMIN");

            //Assign some books for the first user
            Set<LentBook> lentBooks = new HashSet<>();
            lentBooks.add(new LentBook(users.get(0), books.stream().findFirst().get()));
            lentBooks.add(new LentBook(users.get(0), books.stream().skip(4).findFirst().get()));
            lentBooks.add(new LentBook(users.get(0), books.stream().skip(5).findFirst().get()));
            lentBooks.add(new LentBook(users.get(0), books.stream().skip(7).findFirst().get()));
            lentBooks.add(new LentBook(users.get(1), books.stream().skip(1).findFirst().get()));
            lentBooks.add(new LentBook(users.get(1), books.stream().skip(5).findFirst().get()));
            lentBooks.add(new LentBook(users.get(1), books.stream().skip(2).findFirst().get()));
            lentBooks.add(new LentBook(users.get(1), books.stream().skip(6).findFirst().get()));

            lentBooks.forEach(em::persist);

            em.getTransaction().commit();
        }

    }


    private static Set<Book> getBooks() {
        Set<Book> books = new HashSet<>();

        Book book1 = new Book("The Great Gatsby", 1925, "Francis Scott Fitzgerald", Book.Genre.ROMANCE);
        books.add(book1);
        Book book2 = new Book("To Kill a Mockingbird", 1960, "Harper Lee", Book.Genre.CRIME);
        books.add(book2);
        Book book3 = new Book("1984", 1949, "George Orwell", Book.Genre.COMEDY);
        books.add(book3);
        Book book4 = new Book("Pride and Prejudice", 1813, "Jane Austen", Book.Genre.COOKBOOK);
        books.add(book4);
        Book book5 = new Book("The Catcher in the Rye", 1951, "J.D. Salinger", Book.Genre.AUTOBIOGRAPHY);
        books.add(book5);
        Book book6 = new Book("Animal Farm", 1945, "George Orwell", Book.Genre.FOLKLORE);
        books.add(book6);
        Book book7 = new Book("Lord of the Flies", 1954, "William Golding", Book.Genre.MATHEMATICS);
        books.add(book7);
        Book book8 = new Book("The Hobbit", 1937, "J.R.R. Tolkien", Book.Genre.SATIRE);
        books.add(book8);
        Book book9 = new Book("The Lord of the Rings", 1954, "J.R.R. Tolkien", Book.Genre.ART);
        books.add(book9);
        Book book10 = new Book("The Da Vinci Code", 2003, "Dan Brown", Book.Genre.FAIRY_TALE);
        books.add(book10);
        return books;
    }

    private static List<User> getUsers() {
        List<User> users = new ArrayList<>();

        users.add(new User("user1", "user1"));
        users.add(new User("user2", "user2"));

        User admin = new User("admin", "admin1234");
        admin.addRole(new Role("ADMIN"));
        users.add(admin);

        return users;
    }
}
