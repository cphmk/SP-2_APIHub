package dat.services;

import dat.daos.BookDAO;
import dat.dtos.BookDTO;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class BookService {



        private  BookDAO bookDAO;

        public BookService(EntityManagerFactory emf) {
            this.bookDAO = bookDAO.getInstance(emf);
        }

        public BookDTO read(int id) {
            return bookDAO.read(id);
        }

        public List<BookDTO> readAll() {
            return bookDAO.readAll();
        }

        public BookDTO create(BookDTO hotelDTO) {
            return BookDAO.create(hotelDTO);
        }

        public BookDTO update(int id, BookDTO hotelDTO) {
            return bookDAO.update(id, hotelDTO);
        }

        public void delete(int id) {
            bookDAO.delete(id);
        }

        public boolean validatePrimaryKey(Integer id) {
            return bookDAO.validatePrimaryKey(id);
        }

         public boolean validateHotelDTO(BookDTO bookDTO) {
            return
                bookDTO.getTitle() != null && !bookDTO.getTitle().isEmpty() &&  // Checking if title is not null or empty
                bookDTO.getYear() != null &&  //  checking if year is not null
                bookDTO.getLoanedOutDate() != null &&   // checking if loanedOutDate is not null
                bookDTO.getGenre() != null &&   // checking if genre is not null
                bookDTO.getLibraries() != null && !bookDTO.getLibraries().isEmpty() && // Checking if Libraries is not null or empty
                bookDTO.getUsers() != null; // Checking if users is not null or empty
        }
}
