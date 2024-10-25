package dat.entities;

import dat.dtos.BookDTO;
import dat.security.entities.User;
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
    @Column(name = "author", nullable = false)
    private String author;

    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "genre", nullable = true)
    private Genre genre;

    @Setter
    @ManyToOne
    @JoinColumn(name = "User_id", nullable = true)
    private User user;

    public Book(String title, Integer year, String author, Genre genre) {
        this.title = title;
        this.year = year;
        this.author = author;
        this.genre = genre;
    }

    public Book(BookDTO bookDTO) {
        this.title = bookDTO.getTitle();
        this.year = bookDTO.getYear();
        this.author = bookDTO.getAuthor();
        this.genre = bookDTO.getGenre();
    }

    public enum Genre {
        FICTION,
        NON_FICTION,
        SCIENCE_FICTION,
        FANTASY,
        MYSTERY,
        THRILLER,
        ROMANCE,
        HORROR,
        BIOGRAPHY,
        AUTOBIOGRAPHY,
        HISTORY,
        COOKBOOK,
        ART,
        SCIENCE,
        MATHEMATICS,
        PHILOSOPHY,
        RELIGION,
        SELF_HELP,
        TRAVEL,
        GUIDE,
        DIARY,
        JOURNAL,
        POETRY,
        PLAY,
        COMIC,
        GRAPHIC_NOVEL,
        FAIRY_TALE,
        FOLKLORE,
        MYTHOLOGY,
        LEGEND,
        ESSAY,
        SPEECH,
        MEMOIR,
        CRIME,
        DRAMA,
        SATIRE,
        HUMOR,
        PARODY,
        FABLE,
        ALLEGORY,
        PROPAGANDA,
        TRAGEDY,
        COMEDY,
        ROMANTIC_COMEDY,
        ACTION,
        ADVENTURE,
        MYSTERY_ADVENTURE,
        HORROR_ADVENTURE,
        ROMANTIC_ADVENTURE,
        SCIENCE_FICTION_ADVENTURE,
        FANTASY_ADVENTURE,
        HISTORICAL_FICTION,
        HISTORICAL_ROMANCE,
        HISTORICAL_ADVENTURE,
        HISTORICAL_MYSTERY,
        HISTORICAL_THRILLER,
        HISTORICAL_FANTASY,
        HISTORICAL_HORROR,
        HISTORICAL_BIOGRAPHY,
        HISTORICAL_AUTOBIOGRAPHY,
        HISTORICAL_DIARY,
        HISTORICAL_JOURNAL,
        HISTORICAL_POETRY,
        HISTORICAL_PLAY,
        HISTORICAL_COMIC,
        HISTORICAL_GRAPHIC_NOVEL,
        HISTORICAL_FAIRY_TALE,
        HISTORICAL_FOLKLORE,
        HISTORICAL_MYTHOLOGY,
        HISTORICAL_LEGEND,
        HISTORICAL_ESSAY,
        HISTORICAL_SPEECH,
        HISTORICAL_MEMOIR,
        HISTORICAL_CRIME,
        HISTORICAL_DRAMA,
        HISTORICAL_SATIRE,
        HISTORICAL_HUMOR
    }
}