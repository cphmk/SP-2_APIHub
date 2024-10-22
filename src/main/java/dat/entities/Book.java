package dat.entities;

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
}

