package productlisting.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productlisting.model.db.Product;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    Optional<Product> getByProdId(String prodId);

    List<Product> getProductsByMerchantId(String id);

    List<Product> getProductsByInventoryGreaterThan(Integer inventory);

}
