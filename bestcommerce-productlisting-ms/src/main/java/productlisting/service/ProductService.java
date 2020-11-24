package productlisting.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import productlisting.model.ProductResponseModel;
import productlisting.model.db.Product;
import productlisting.repo.ProductRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    public List<ProductResponseModel> getProducts() {
        return productRepo.findAll().stream()
                .map(product -> objectMapper.convertValue(product, ProductResponseModel.class))
                .collect(Collectors.toList());
    }

    public Optional<Product> getOne(String prodId) {
        return productRepo.getByProdId(prodId);
    }

    public List<ProductResponseModel> getMerchantProducts(String id) {
        return productRepo.getProductsByMerchantId(id).stream()
                .map(product -> objectMapper.convertValue(product, ProductResponseModel.class))
                .collect(Collectors.toList());
    }
}
