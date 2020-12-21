package productlisting.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productlisting.model.ProductResponseModel;
import productlisting.service.ProductService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/rest-template/merchants/{id}/products")
public class ProductControllerRest {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductResponseModel>> getProductsByMerchantId(@PathVariable String id) {
        List<ProductResponseModel> products = productService.getMerchantProducts(id);
        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(products);
    }

}
