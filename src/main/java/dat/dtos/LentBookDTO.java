package dat.dtos;

import dat.entities.LentBook;
import lombok.*;
import dk.bugelhartmann.UserDTO;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString

public class LentBookDTO {

    private Integer id;
    private UserDTO user;
    private BookDTO book;
    private LocalDateTime lentDate;
    private LocalDateTime returnDate;

    public LentBookDTO(LentBook lentBook) {
        this.id = lentBook.getId();
        this.user = new UserDTO(lentBook.getUser().getUsername(), lentBook.getUser().getPassword());
        this.book = new BookDTO(lentBook.getBook());
        this.lentDate = lentBook.getLentDate();
        this.returnDate = lentBook.getReturnDate();
    }

}
