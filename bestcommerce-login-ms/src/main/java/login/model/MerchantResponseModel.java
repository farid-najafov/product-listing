package login.model;

import login.model.product.Product;
import login.util.MerchantType;
import lombok.Data;

import java.util.List;

@Data
public class MerchantResponseModel {

    private String name;
    private String ownerName;
    private String address;
    private String phoneNumber;
    private String email;
    private String encryptedPassword;
    private String randomId;
    private MerchantType type;
    private List<Product> products;

}
