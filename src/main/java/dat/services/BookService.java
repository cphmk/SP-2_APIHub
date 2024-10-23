package dat.services;

import dat.daos.BookDAO;
import dat.daos.LendDAO;
import dat.dtos.BookDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class BookService {


    private static BookDAO bookDAO;
    private static BookService instance;

    public static BookService getInstance(EntityManagerFactory _emf) {
        if (instance == null) {
            instance = new BookService();
            bookDAO = BookDAO.getInstance(_emf);
        }
        return instance;
    }


    public BookDTO read(int id) {
        return bookDAO.read(id);
    }

    public List<BookDTO> readAll() {
        return bookDAO.readAll();
    }

    public BookDTO create(BookDTO bookDTO) {
        return bookDAO.create(bookDTO);
    }

    public BookDTO update(int id, BookDTO bookDTO) {
        return bookDAO.update(id, bookDTO);
    }

    public void delete(int id) {
        bookDAO.delete(id);
    }

    public boolean validatePrimaryKey(Integer id) {
        return bookDAO.validatePrimaryKey(id);
    }

    public boolean validateBookDTO(BookDTO bookDTO) {
        return
                bookDTO.getTitle() != null && !bookDTO.getTitle().isEmpty() &&  // Checking if title is not null or empty
                        bookDTO.getYear() != null &&  //  checking if year is not null
                        bookDTO.getLoanedOutDate() != null &&   // checking if loanedOutDate is not null
                        bookDTO.getGenre() != null;   // checking if genre is not null

    }
}
