package productlisting.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import productlisting.model.db.Product;
import productlisting.repo.ProductRepo;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepo productRepo;
    @Mock
    private ObjectMapper objectMapper;
    @InjectMocks
    private ProductService productService;

    @Test
    void getProducts() {
        var products = List.of(new Product());
        given(productRepo.findAll()).willReturn(products);
        var products1 = productService.getProducts();
        then(productRepo).should().findAll();
        assertThat(products1).isNotEmpty();
    }

    @Test
    void getOne() {
        given(productRepo.getByProdId(anyString())).willReturn(Optional.empty());
        var one = productService.getOne("1");
        then(productRepo).should().getByProdId("1");
        assertThat(one).isNotPresent();
    }

    @Test
    void getMerchantProducts() {
        given(productRepo.getProductsByMerchantId(anyString())).willReturn(List.of(new Product()));
        var merchantProducts = productService.getMerchantProducts("2");
        then(productRepo).should().getProductsByMerchantId("2");
        assertThat(merchantProducts).isNotEmpty();
    }
}