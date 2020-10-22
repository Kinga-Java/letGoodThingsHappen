package pl.coderslab.charity.donation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface DonationRepository extends JpaRepository<Donation, Long> {

    @Query("SELECT SUM(d.quantity) FROM Donation d")
    public Long donationQuantitiesSum();

    @Query("SELECT COUNT(d.id) FROM Donation d")
    public Long donationQuantity();


}
