package dat.config;

import dat.entities.Book;
import dat.entities.Employee;
import dat.entities.Genre;
import dat.entities.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.HashSet;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

        //Set<Genre> genres = getGenres();
        Set<Employee> employees1 = getEmployees1();
        Set<Employee> employees2 = getEmployees2();
        Set<Book> books = getBooks();

        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();

            //genres.forEach(em::persist);
            books.forEach(em::persist);

            Library library1 = new Library("Library of Congress", "Washington D.C.");
            library1.setEmployees(getEmployees1());

            Library library2 = new Library("New York Public Library", "New York City");
            library2.setEmployees(getEmployees2());

            em.persist(library1);
            em.persist(library2);

            em.getTransaction().commit();
        }

    }

    /*private static Set<Genre> getGenres() {
        Set<Genre> genres = new HashSet<>();

        genres.add(new Genre("Fiction"));
        genres.add(new Genre("Non-fiction"));
        genres.add(new Genre("Mystery"));
        genres.add(new Genre("Thriller"));
        genres.add(new Genre("Romance"));
        genres.add(new Genre("Fantasy"));
        genres.add(new Genre("Science Fiction"));
        genres.add(new Genre("Historical Fiction"));
        genres.add(new Genre("Horror"));
        genres.add(new Genre("Adventure"));
        genres.add(new Genre("Young Adult"));
        genres.add(new Genre("Children's Literature"));
        genres.add(new Genre("Biography"));
        genres.add(new Genre("Autobiography"));
        genres.add(new Genre("Self-help"));
        genres.add(new Genre("Memoir"));
        genres.add(new Genre("Dystopian"));
        genres.add(new Genre("Classics"));
        genres.add(new Genre("Graphic Novel"));
        genres.add(new Genre("Poetry"));

        return genres;
    }*/

    private static Set<Employee> getEmployees1() {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee(38, "John Doe", "Male", "johndoe@mail.com"));
        employees.add(new Employee(29, "Alice Smith", "Female", "alicesmith@mail.com"));
        employees.add(new Employee(35, "Bob Johnson", "Male", "bobjohnson@mail.com"));
        employees.add(new Employee(50, "Charlie Brown", "Male", "charliebrown@mail.com"));
        employees.add(new Employee(28, "Diana Prince", "Female", "dianaprince@mail.com"));

        return employees;
    }

    private static Set<Employee> getEmployees2() {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee(38, "Jane Doe", "Female", "janedoe@mail.com"));
        employees.add(new Employee(45, "Eve Adams", "Female", "eveadams@mail.com"));
        employees.add(new Employee(32, "Frank Castle", "Male", "frankcastle@mail.com"));
        employees.add(new Employee(39, "Grace Lee", "Female", "gracelee@mail.com"));
        employees.add(new Employee(41, "Henry Ford", "Male", "henryford@mail.com"));

        return employees;
    }

    private static Set<Book> getBooks() {
        Set<Book> books = new HashSet<>();

        Book book1 = new Book("The Great Gatsby", 1925, "F. Scott Fitzgerald", 218, 1);
        book1.setGenres(Set.of(new Genre("Fiction"), new Genre("Classics")));

        Book book2 = new Book("To Kill a Mockingbird", 1960, "Harper Lee", 281, 1);
        book2.setGenres(Set.of(new Genre("Fiction"), new Genre("Classics")));

        Book book3 = new Book("1984", 1949, "George Orwell", 328, 1);
        book3.setGenres(Set.of(new Genre("Fiction"), new Genre("Science Fiction")));

        Book book4 = new Book("Pride and Prejudice", 1813, "Jane Austen", 279, 1);
        book4.setGenres(Set.of(new Genre("Fiction"), new Genre("Romance"), new Genre("Classics")));

        Book book5 = new Book("The Catcher in the Rye", 1951, "J.D. Salinger", 277, 1);
        book5.setGenres(Set.of(new Genre("Fiction"), new Genre("Classics")));

        Book book6 = new Book("Animal Farm", 1945, "George Orwell", 141, 1);
        book6.setGenres(Set.of(new Genre("Fiction"), new Genre("Classics")));

        Book book7 = new Book("Lord of the Flies", 1954, "William Golding", 182, 1);
        book7.setGenres(Set.of(new Genre("Fiction"), new Genre("Classics")));

        Book book8 = new Book("The Hobbit", 1937, "J.R.R. Tolkien", 310, 1);
        book8.setGenres(Set.of(new Genre("Fiction"), new Genre("Fantasy")));

        Book book9 = new Book("The Lord of the Rings", 1954, "J.R.R. Tolkien", 1178, 1);
        book9.setGenres(Set.of(new Genre("Fiction"), new Genre("Fantasy")));

        Book book10 = new Book("The Da Vinci Code", 2003, "Dan Brown", 489, 1);
        book10.setGenres(Set.of(new Genre("Fiction"), new Genre("Mystery"), new Genre("Thriller")));

        return books;
    }

}
