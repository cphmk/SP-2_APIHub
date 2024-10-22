package dat.entities;

import dat.dtos.UserDTO;
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
@Table(name = "User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "User_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Setter
    @Column(name = "Password", nullable = false)
    private String password;

    @Setter
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    @Setter
    @ManyToMany(mappedBy = "users", fetch = FetchType.LAZY) // Should maybe be @ManyToOne
    private Set<Library> libraries = new HashSet<>();

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(UserDTO userDTO) {
        this.username = userDTO.getUsername();
        this.password = userDTO.getPassword();
        this.books = userDTO.getBooks();
        this.libraries = userDTO.getLibraries();
    }
}
