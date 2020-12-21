package productlisting.controller;

import lombok.AllArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productlisting.model.ProductResponseModel;
import productlisting.model.db.Product;
import productlisting.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public ResponseEntity<CollectionModel<EntityModel<ProductResponseModel>>> getProducts() {

        List<EntityModel<ProductResponseModel>> products = productService.getProducts().stream().map(pr ->
                EntityModel.of(pr, linkTo(methodOn(this.getClass()).getOneProduct(pr.getProdId())).withSelfRel()))
                .collect(Collectors.toList());

        CollectionModel<EntityModel<ProductResponseModel>> collectionModel = CollectionModel.of(products)
                .add(linkTo(methodOn(this.getClass()).getProducts()).withRel("products"));

        return products.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(collectionModel);
    }

    @GetMapping("/{prodId}")
    public ResponseEntity<EntityModel<Product>> getOneProduct(@PathVariable("prodId") String prodId) {
        return productService.getOne(prodId)
                .map(body -> ResponseEntity.ok(EntityModel.of(body,
                        linkTo(methodOn(this.getClass()).getOneProduct(prodId)).withSelfRel())))
                .orElse(ResponseEntity.notFound().build());
    }

}
