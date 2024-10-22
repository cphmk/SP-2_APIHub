package dat.entities;

import dat.dtos.GenreDTO;
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
@Table(name = "Genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Genre_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "Genre_name", nullable = false, unique = true)
    private String genreName;

    @ManyToMany(mappedBy = "genres", fetch = FetchType.LAZY)
    private Set<Book> books = new HashSet<>();

    public Genre(String genreName) {
        this.genreName = genreName;
    }

    public Genre(GenreDTO genreDTO) {
        this.genreName = genreDTO.getGenreName();
        this.books = genreDTO.getBooks();
    }
}
