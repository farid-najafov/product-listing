package productlisting.model.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String prodId;
    private String name;
    private String description;
    private Double unitPrice;
    private Integer inventory;

    @ManyToOne
    private ProductCategory category;

    @ManyToMany(targetEntity = PaymentOption.class)
    @JoinTable(name = "pr_payment_opts")
    private List<PaymentOption> paymentOptions;

    @ManyToMany(targetEntity = DeliveryOption.class)
    @JoinTable(name = "pr_delivery_opts")
    private List<DeliveryOption> deliveryOptions;

    private String merchantId;

}
