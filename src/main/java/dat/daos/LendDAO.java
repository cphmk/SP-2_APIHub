package dat.daos;

import dat.dtos.BookDTO;
import dat.dtos.LentBookDTO;
import dat.entities.Book;
import dat.entities.LentBook;
import dat.security.entities.User;
import dk.bugelhartmann.UserDTO;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

public class LendDAO implements IDAO<LentBookDTO, Long> {

    private static LendDAO instance;
    private static EntityManagerFactory emf;

    public static LendDAO getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new LendDAO();
        }
        return instance;
    }

    public LentBookDTO lendBook(UserDTO userDTO, Long bookId) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();


            User user = em.find(User.class, userDTO.getUsername());
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            }

            Book book = em.find(Book.class, bookId);
            if (book == null) {
                throw new EntityNotFoundException("Book not found");
            }

            // Set the lent date to today and the return date to 28 days from now
            LocalDateTime lentDate = LocalDateTime.now();
            LocalDateTime returnDate = lentDate.plusDays(28);


            LentBook lentBook = new LentBook(user, book, lentDate, returnDate);
            em.persist(lentBook);

            em.getTransaction().commit();
            return new LentBookDTO(lentBook);
        }
    }

    @Override
    public LentBookDTO read(Long aLong) {
        try(EntityManager em = emf.createEntityManager()) {
            LentBook lentBook = em.find(LentBook.class, aLong);
            if (lentBook == null) {
                throw new EntityNotFoundException("LentBook not found");
            }
            return new LentBookDTO(lentBook);
        }
    }

    public List<LentBookDTO> readUserLends(UserDTO userDTO) {
        try (EntityManager em = emf.createEntityManager()) {
            User user = em.find(User.class, userDTO.getUsername());
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            }
            System.out.println(user);
            TypedQuery<LentBook> query = em.createQuery("SELECT lb FROM LentBook lb WHERE lb.user = :user", LentBook.class);
            query.setParameter("user", user);
            List<LentBook> lentBooks = query.getResultList();
            System.out.println(lentBooks);
            if (lentBooks.isEmpty()) {
                throw new EntityNotFoundException("No lent books found");
            }
            else {
                return lentBooks.stream().map(LentBookDTO::new).toList();
            }
        }
    }

    @Override
    public List<LentBookDTO> readAll() {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<LentBook> query = em.createQuery("SELECT lb FROM LentBook lb", LentBook.class);
            List<LentBook> lentBooks = query.getResultList();
            if (lentBooks.isEmpty()) {
                throw new EntityNotFoundException("No lent books found");
            }
            return lentBooks.stream().map(LentBookDTO::new).toList();
        }
    }

    @Override
    public LentBookDTO create(LentBookDTO lentBookDTO) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            LentBook lentBook = new LentBook(lentBookDTO);

            em.persist(lentBook);

            em.getTransaction().commit();
            return new LentBookDTO(lentBook);
        } catch (Exception e) {
            throw new RuntimeException("Error creating LentBook", e);
        }
    }

    @Override
    public LentBookDTO update(Long aLong, LentBookDTO lentBookDTO) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            LentBook lentBook = em.find(LentBook.class, aLong);
            if (lentBook == null) {
                throw new EntityNotFoundException("LentBook not found");
            }

            // Book should not be updated, delete the lentbook and create a new one instead.
            /*if (lentBookDTO.getBook() != null && !lentBook.getBook().equals(lentBookDTO.getBook())) {
                throw new IllegalArgumentException("Book cannot be updated for an existing loan");
            }*/

            lentBook.setReturnDate(lentBookDTO.getReturnDate());
            lentBook.setLentDate(lentBookDTO.getLentDate());

            LentBook updatedLentBook = em.merge(lentBook);

            em.getTransaction().commit();

            return new LentBookDTO(updatedLentBook);
        } catch (Exception e) {
            throw new RuntimeException("Error updating LentBook", e);
        }

    }

    public LentBookDTO updateOwnLoan(UserDTO userDTO, Long bookId, LentBookDTO lentBookDTO) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();

            // Verify that the user owns this LentBook
            TypedQuery<LentBook> query = em.createQuery(
                    "SELECT lb FROM LentBook lb WHERE lb.user.username = :username AND lb.book.id = :bookId",
                    LentBook.class
            );
            query.setParameter("username", userDTO.getUsername());
            query.setParameter("bookId", bookId);

            LentBook lentBook = query.getSingleResult();
            if (lentBook == null) {
                throw new EntityNotFoundException("Lent book not found for this user");
            }

            // Update only the returnDate and lentDate
            lentBook.setReturnDate(lentBookDTO.getReturnDate());
            lentBook.setLentDate(lentBookDTO.getLentDate());

            LentBook updatedLentBook = em.merge(lentBook);

            em.getTransaction().commit();
            return new LentBookDTO(updatedLentBook);
        } catch (Exception e) {
            throw new RuntimeException("Error updating LentBook", e);
        } finally {
            em.close();
        }
    }

    public LentBook getLentBook(UserDTO userDTO, Long bookId) {
        EntityManager em = emf.createEntityManager();
        try {
            User user = em.find(User.class, userDTO.getUsername());
            if (user == null) {
                throw new EntityNotFoundException("User not found");
            }

            // Query to find the specific LentBook for this user and book
            TypedQuery<LentBook> query = em.createQuery(
                    "SELECT lb FROM LentBook lb WHERE lb.user = :user AND lb.book.id = :bookId",
                    LentBook.class
            );
            query.setParameter("user", user);
            query.setParameter("bookId", bookId);

            return query.getSingleResult();
        } catch (NoResultException e) {
            throw new EntityNotFoundException("Lent book not found for this user and book");
        } finally {
            em.close();
        }
    }

    @Override
    public void delete(Long aLong) {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            LentBook lentBook = em.find(LentBook.class, aLong);
            if (lentBook == null) {
                throw new EntityNotFoundException("LentBook not found");
            }
            em.remove(lentBook);
            em.getTransaction().commit();
        }

    }

    @Override
    public boolean validatePrimaryKey(Long aLong) {
        try(EntityManager em = emf.createEntityManager()) {
        LentBook lentBook = em.find(LentBook.class, aLong);
        return lentBook != null;
        }
    }
}
