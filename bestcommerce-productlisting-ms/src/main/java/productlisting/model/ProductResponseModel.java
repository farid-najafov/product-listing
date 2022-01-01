package productlisting.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.server.core.Relation;
import productlisting.model.db.DeliveryOption;
import productlisting.model.db.PaymentOption;
import productlisting.model.db.ProductCategory;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Relation(collectionRelation = "products")
public class ProductResponseModel {

    private String prodId;
    private String name;
    private String description;
    private Double unitPrice;
    private Integer inventory;
    private ProductCategory category;
    private List<PaymentOption> paymentOptions;
    private List<DeliveryOption> deliveryOptions;

    private String merchantId;
    
}
