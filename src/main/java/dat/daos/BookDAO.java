package dat.daos;


import dat.dtos.BookDTO;
import dat.entities.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class BookDAO implements IDAO<BookDTO, Integer>{

    private static BookDAO instance;
    private static EntityManagerFactory emf;

    public static BookDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookDAO();
        }
        return instance;
    }

    @Override
    public BookDTO read(Integer integer) {
        try(EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, integer);
            return new BookDTO(book);
        }
    }
    public BookDTO readGenre(Book.Genre genre) {
        try(EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, genre);
            return new BookDTO(book);
        }
    }
    public BookDTO readTitle(String title) {
        try(EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, title);
            return new BookDTO(book);
        }
    }
    public BookDTO readYear(Integer year) {
        try(EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, year);
            return new BookDTO(book);
        }
    }
    public BookDTO readAuthor(String author) {
        try(EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, author);
            return new BookDTO(book);
        }
    }


    @Override
    public List<BookDTO> readAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<BookDTO> query = em.createQuery("SELECT new dat.dtos.BookDTO(b) FROM Book b", BookDTO.class);

            return query.getResultList();
        }
    }

    @Override
    public BookDTO create(BookDTO bookDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Book book = new Book(bookDTO);

            TypedQuery<Book> query = em.createQuery("SELECT b FROM Book b WHERE b.title = :title", Book.class);
            query.setParameter("title", bookDTO.getTitle());
            //If there exists no books, persist it
            if (query.getResultList().isEmpty()) {
                em.persist(book);
            }
            else {
                book = query.getSingleResult();
            }

            em.getTransaction().commit();
            return new BookDTO(book);
        }
    }

    @Override
    public BookDTO update(Integer integer, BookDTO bookDTO) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            Book book = em.find(Book.class, integer);
            book.setGenre(bookDTO.getGenre());
            book.setTitle(bookDTO.getTitle());
            book.setYear(bookDTO.getYear());
            Book mergedBook = em.merge(book);
            em.getTransaction().commit();
            return mergedBook != null ? new BookDTO(mergedBook) : null ;

        }
    }

    @Override
    public void delete(Integer integer) {
    try (EntityManager em = emf.createEntityManager()){
        em.getTransaction().begin();
        Book book = em.find(Book.class, integer);
        if(book != null ) {
            em.remove(book);
        }
        em.getTransaction().commit();
    }
    }

    @Override
    public boolean validatePrimaryKey(Integer integer) {
        try (EntityManager em = emf.createEntityManager()) {
            Book book = em.find(Book.class, integer);

            return book != null;
        }
        }
}
