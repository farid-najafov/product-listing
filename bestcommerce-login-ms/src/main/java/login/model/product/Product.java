package login.model.product;

import lombok.Data;

import java.util.List;

@Data
public class Product {

    private String prodId;
    private String name;
    private String description;
    private Double unitPrice;
    private Integer inventory;
    private ProductCategory category;
    private List<PaymentOption> paymentOptions;
    private List<DeliveryOption> deliveryOptions;

}
