package pl.coderslab.charity.institution;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.donation.Donation;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name= Institution.TABLE)
public class Institution {
    public final static String TABLE = "institution";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(nullable = false)
    private String name;

    @NotBlank
    @Column(nullable = false)
    private String description;


}
