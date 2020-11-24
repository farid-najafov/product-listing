package productlisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productlisting.model.db.DeliveryOption;

@Repository
public interface DeliveryOptionRepo extends JpaRepository<DeliveryOption, Long> {

    DeliveryOption findByOption(String option);
}
