package pl.coderslab.charity.user;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = User.TABLE)
public class User {
    public final static String TABLE = "user";

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @NotBlank
    @Column(nullable = false)
    @Size(min = 8)
    private String password;

    @NotBlank
    @Transient
    private String repeatPassword;

    @NotBlank
    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String role;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", role='" + role + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
