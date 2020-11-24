package login.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import login.model.MerchantResponseModel;
import login.model.db.MerchantEntity;
import login.model.product.Product;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class MerchantServiceSync {

    private static final String HTTP_URL = "http://localhost:8080/api/v1/rest-template/merchants/{id}/products";
    private final RestTemplate restTemplate;
    private final MerchantService merchantService;
    private final ObjectMapper objectMapper;


    @HystrixCommand(fallbackMethod = "fallbackGetProductsByMerchantId")
    public Optional<MerchantResponseModel> getProductsByMerchantId(String merchantId) {

        URI url = UriComponentsBuilder
                .fromHttpUrl(HTTP_URL)
                .build(merchantId);

        ResponseEntity<List<Product>> exchange = restTemplate
                .exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<>() {
                });
        List<Product> products = exchange.getBody();
        MerchantEntity merchantById = merchantService.findMerchantById(merchantId);
        MerchantResponseModel merchant = objectMapper.convertValue(merchantById, MerchantResponseModel.class);
        merchant.setProducts(products);

        return Optional.of(merchant);

    }

    private Optional<MerchantResponseModel> fallbackGetProductsByMerchantId(String merchantId) {
        log.info(">>>>>> handling ResourceAccessException <<<<<<<");
        return Optional.empty();
    }
}
