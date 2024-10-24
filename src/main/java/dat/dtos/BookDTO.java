package dat.dtos;

import dat.entities.Book;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder

public class BookDTO {
    private Integer id;
    private String title;
    private Integer year;
    private String author;
    private Book.Genre genre;


    public BookDTO(String title, Integer year, String author, Book.Genre genre) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public BookDTO(Book book) {
        this.id = book.getId();
        this.title = book.getTitle();
        this.year = book.getYear();
        this.author = book.getAuthor();
        this.genre = book.getGenre();
    }
}
