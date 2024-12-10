package dat.entities;


import dat.dtos.LentBookDTO;
import jakarta.persistence.*;
import dk.bugelhartmann.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import dat.security.entities.User;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table
public class LentBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @Column
    private LocalDateTime lentDate;

    @Column
    private LocalDateTime returnDate;

    public LentBook(User user, Book book, LocalDateTime lentDate, LocalDateTime returnDate) {
        this.user = user;
        this.book = book;
        this.lentDate = lentDate;
        this.returnDate = returnDate;
    }

    public LentBook(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public LentBook(LentBookDTO lentBookDTO) {
        this.user = new User(lentBookDTO.getUser().getUsername(), lentBookDTO.getUser().getPassword());
        this.book = new Book(lentBookDTO.getBook());
    }


    @PrePersist
    public void prePersist() {
        lentDate = LocalDateTime.now();
    }



}
