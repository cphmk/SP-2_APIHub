package dat.entities;

import dat.dtos.BookDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "title", nullable = false, unique = true)
    private String title;

    @Setter
    @Column(name = "release_year", nullable = false)
    private Integer year;

    @Setter
    @Column(name = "loaned_out", nullable = false)
    private boolean loanedOut;

    @Setter
    @Column(name = "loaned_out_date", nullable = true)
    private LocalDateTime loanedOutDate;

    @Setter
    @ManyToOne
    @JoinColumn(name = "Library_id", nullable = true)
    private Library library;

    @Setter
    @ManyToOne
    @JoinColumn(name = "User_id", nullable = true)
    private User user;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Books_Genres",
            joinColumns = @JoinColumn(name = "Book_id"),
            inverseJoinColumns = @JoinColumn(name = "Genre_id")
    )
    private Set<Genre> genres = new HashSet<>();

    public Book(String title, Integer year, boolean loanedOut, LocalDateTime loanedOutDate, Set<Genre> genres) {
        this.title = title;
        this.year = year;
        this.loanedOut = loanedOut;
        this.loanedOutDate = loanedOutDate;
        this.genres = genres;
    }

    public Book(String title, Integer year, boolean loanedOut, LocalDateTime loanedOutDate, Library library, User user) {
        this.title = title;
        this.year = year;
        this.loanedOut = loanedOut;
        this.loanedOutDate = loanedOutDate;
        this.library = library;
        this.user = user;
    }

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.year = bookDTO.getYear();
        this.loanedOut = bookDTO.isLoanedOut();
        this.loanedOutDate = bookDTO.getLoanedOutDate();
        this.genres = bookDTO.getGenres();
        this.library = bookDTO.getLibrary();
    }

}