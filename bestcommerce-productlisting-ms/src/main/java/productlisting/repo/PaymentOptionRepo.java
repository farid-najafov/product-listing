package productlisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productlisting.model.db.PaymentOption;

@Repository
public interface PaymentOptionRepo extends JpaRepository<PaymentOption, Long> {

    PaymentOption findByOption(String option);
}
