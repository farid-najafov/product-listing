package login.model;

import login.util.MerchantType;
import lombok.Data;

@Data
public class CreateMerchantReq {

    private String name;
    private String ownerName;
    private String address;
    private String phoneNumber;
    private String email;
    private String password;
    private MerchantType type;

}
