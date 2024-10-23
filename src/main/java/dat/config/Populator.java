package dat.config;

import dat.entities.Book;
import dat.entities.Employee;
import dat.entities.Library;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Populator {
    public static void main(String[] args) {

        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

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


    private static Set<Employee> getEmployees1() {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee("John Doe", 25, Employee.EmployeeGender.MALE, "johndoe@mail.com"));
        employees.add(new Employee("Alice Smith", 30, Employee.EmployeeGender.FEMALE, "alicesmith@mail.com"));
        employees.add(new Employee("Bob Johnson", 35, Employee.EmployeeGender.OTHER, "bobjohnson@mail.com"));
        employees.add(new Employee("Charlie Brown", 40, Employee.EmployeeGender.MALE, "charliebrown@mail.com"));
        employees.add(new Employee("Diana Prince", 45, Employee.EmployeeGender.FEMALE, "dianaprince@mail.com"));

        return employees;
    }

    private static Set<Employee> getEmployees2() {
        Set<Employee> employees = new HashSet<>();

        employees.add(new Employee("Jane Doe", 38, Employee.EmployeeGender.FEMALE, "janedoe@mail.com"));
        employees.add(new Employee("Eve Adams", 45, Employee.EmployeeGender.OTHER, "eveadams@mail.com"));
        employees.add(new Employee("Frank Castle", 32, Employee.EmployeeGender.MALE, "frankcastle@mail.com"));
        employees.add(new Employee("Grace Lee", 39, Employee.EmployeeGender.FEMALE, "gracelee@mail.com"));
        employees.add(new Employee("Henry Ford", 41, Employee.EmployeeGender.MALE, "henryford@mail.com"));

        return employees;
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
