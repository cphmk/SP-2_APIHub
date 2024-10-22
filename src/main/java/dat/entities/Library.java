package dat.entities;

import dat.dtos.LibraryDTO;
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
@Table(name = "Library")
public class Library {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Library_id", nullable = false, unique = true)
    private Integer id;

    @Setter
    @Column(name = "Library_name", nullable = false, unique = true)
    private String libraryName;

    @Setter
    @Column(name = "Library_address", nullable = false)
    private String libraryAddress;

    @Setter
    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Employee> employees = new HashSet<>();

    @Setter
    @OneToMany(mappedBy = "library", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    @Setter
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY) // Should maybe be @OneToMany
    @JoinTable(
            name = "Library_Users",
            joinColumns = @JoinColumn(name = "Library_id"),
            inverseJoinColumns = @JoinColumn(name = "User_id")
    )
    private Set<User> users = new HashSet<>();

    public Library(String libraryName, String libraryAddress) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
    }

    public Library(LibraryDTO libraryDTO) {
        this.libraryName = libraryDTO.getLibraryName();
        this.libraryAddress = libraryDTO.getLibraryAddress();
        this.employees = libraryDTO.getEmployees();
        this.books = libraryDTO.getBooks();
        this.users = libraryDTO.getUsers();
    }
}
