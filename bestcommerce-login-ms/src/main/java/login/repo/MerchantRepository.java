package login.repo;

import login.model.db.MerchantEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MerchantRepository extends CrudRepository<MerchantEntity, Long> {

    Optional<MerchantEntity> findByEmail(String email);
    Optional<MerchantEntity> findByRandomId(String randomId);

}
