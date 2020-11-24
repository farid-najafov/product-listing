package login.controller;

import login.model.MerchantResponseModel;
import login.service.MerchantServiceSync;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/rest-template")
public class MerchantControllerRest {

    private final MerchantServiceSync merchantService;

    @GetMapping("merchants/{id}/products")
    public ResponseEntity<MerchantResponseModel> getProducts(@PathVariable String id) {
        return merchantService.getProductsByMerchantId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.noContent().build());
    }

}
