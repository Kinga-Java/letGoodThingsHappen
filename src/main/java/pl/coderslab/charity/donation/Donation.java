package pl.coderslab.charity.donation;


import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import pl.coderslab.charity.category.Category;
import pl.coderslab.charity.institution.Institution;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name= Donation.TABLE) @NoArgsConstructor @AllArgsConstructor @Builder
public class Donation {
    public final static String TABLE = "donations";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Min(value = 1)
    private Integer quantity;

    @ManyToMany
    private List<Category> categories;
    @ManyToOne
    private Institution institution;

     @NotBlank
    private String street;

    @NotBlank
    private String city;

    @NotBlank
    private String zipCode;


    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickUpDate;


    @DateTimeFormat(pattern = "H:mm")
    private LocalTime pickUpTime;

    private String pickUpComment;

}
