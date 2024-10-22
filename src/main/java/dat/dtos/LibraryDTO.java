package dat.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class LibraryDTO {
    private Integer id;
    private String libraryName;
    private String libraryAddress;
    private Set<BookDTO> books = new HashSet<>();
    private Set<EmployeeDTO> employees = new HashSet<>();

    public LibraryDTO(String libraryName, String libraryAddress) {
        this.libraryName = libraryName;
        this.libraryAddress = libraryAddress;
    }
}
