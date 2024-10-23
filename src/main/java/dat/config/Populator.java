package dat.config;

import dat.entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        Set<Book> books = getBooks();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            //genres.forEach(em::persist);
            books.forEach(em::persist);


            em.getTransaction().commit();
        }

    }


    private static Set<Book> getBooks() {
        Set<Book> books = new HashSet<>();

        Book book1 = new Book("The Great Gatsby", 1925, "Francis Scott Fitzgerald", true, LocalDateTime.now(), Book.Genre.ROMANCE);
        books.add(book1);
        Book book2 = new Book("To Kill a Mockingbird", 1960, "Harper Lee", false, LocalDateTime.now(), Book.Genre.CRIME);
        books.add(book2);
        Book book3 = new Book("1984", 1949, "George Orwell", false, LocalDateTime.now(), Book.Genre.COMEDY);
        books.add(book3);
        Book book4 = new Book("Pride and Prejudice", 1813, "Jane Austen", false, LocalDateTime.now(), Book.Genre.COOKBOOK);
        books.add(book4);
        Book book5 = new Book("The Catcher in the Rye", 1951, "J.D. Salinger", true, LocalDateTime.now(), Book.Genre.AUTOBIOGRAPHY);
        books.add(book5);
        Book book6 = new Book("Animal Farm", 1945, "George Orwell", false, LocalDateTime.now(), Book.Genre.FOLKLORE);
        books.add(book6);
        Book book7 = new Book("Lord of the Flies", 1954, "William Golding", true, LocalDateTime.now(), Book.Genre.MATHEMATICS);
        books.add(book7);
        Book book8 = new Book("The Hobbit", 1937, "J.R.R. Tolkien", true, LocalDateTime.now(), Book.Genre.SATIRE);
        books.add(book8);
        Book book9 = new Book("The Lord of the Rings", 1954, "J.R.R. Tolkien", true, LocalDateTime.now(), Book.Genre.ART);
        books.add(book9);
        Book book10 = new Book("The Da Vinci Code", 2003, "Dan Brown", false, LocalDateTime.now(), Book.Genre.FAIRY_TALE);
        books.add(book10);
        return books;
    }

}
