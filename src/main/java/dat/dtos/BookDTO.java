package dat.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class BookDTO {
    private Integer id;
    private String title;
    private Integer year;
    private boolean loanedOut;
    private LocalDateTime loanedOutDate;
    private String genre;
    private Set<LibraryDTO> libraries = new HashSet<>();
    private Set<UserDTO> users = new HashSet<>();

    public BookDTO(String title, Integer year, boolean loanedOut, LocalDateTime loanedOutDate) {
        this.title = title;
        this.year = year;
        this.loanedOut = loanedOut;
        this.loanedOutDate = loanedOutDate;
        this.genre = genre;
    }
}
