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
@Table(name = "Employee")
public class Employee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "Employee_id", nullable = false, unique = true)
        private Integer id;

        @Setter
        @Column(name = "Employee_name", nullable = false, unique = true)
        private String employeeName;

        @Setter
        @Column(name = "Employee_age", nullable = false)
        private Integer employeeAge;

        @Setter
        @Enumerated(EnumType.STRING)
        @Column(name = "Employee_gender", nullable = false)
        private String employeeGender;

        @Setter
        @Column(name = "Employee_email", nullable = false)
        private String employeeEmail;

        @Setter
        @ManyToOne
        @JoinColumn(name = "Library_id", nullable = true)
        private Library library;

}
