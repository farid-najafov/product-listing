package signup.model;

import lombok.Data;

@Data
public class CreateMerchantResp {

    private String randomId;
    private String name;
    private String ownerName;
    private String address;
    private String phoneNumber;
    private String email;

}
