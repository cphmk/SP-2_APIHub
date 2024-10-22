package dat.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
public class UserDTO {
    private Integer id;
    private String userName;
    private String userPassword;
    private String userRole;
    private Set<BookDTO> books = new HashSet<>();

    public UserDTO(String userName, String userPassword, String userRole) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.userRole = userRole;
    }
}
