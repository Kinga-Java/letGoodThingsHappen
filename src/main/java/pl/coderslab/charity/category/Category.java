package pl.coderslab.charity.category;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name=Category.TABLE)
public class Category {
    public final static String TABLE = "categories";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String name;
}
