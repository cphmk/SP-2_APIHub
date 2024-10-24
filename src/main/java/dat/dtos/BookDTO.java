package dat.dtos;

import dat.entities.Book;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class BookDTO {
    private Integer id;
    private String title;
    private Integer year;
    private String author;
    private boolean loanedOut;
    private LocalDateTime loanedOutDate;
    private Book.Genre genre;


    public BookDTO(String title, Integer year, String author, boolean loanedOut, LocalDateTime loanedOutDate, Book.Genre genre) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.loanedOut = loanedOut;
        this.loanedOutDate = loanedOutDate;
        this.genre = genre;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.year = book.getYear();
        this.author = book.getAuthor();
        this.loanedOut = book.isLoanedOut();
        this.loanedOutDate = book.getLoanedOutDate();
        this.genre = book.getGenre();
    }
}
