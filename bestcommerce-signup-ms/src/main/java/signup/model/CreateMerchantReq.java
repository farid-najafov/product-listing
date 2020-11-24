package signup.model;

import lombok.Data;
import signup.util.MerchantType;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateMerchantReq {

    @NotNull(message = "name cannot be null")
    @Size(min = 2, message = "name shouldn't be less than 2 characters")
    private String name;

    @NotNull(message = "owner name cannot be null")
    @Size(min = 2, message = "owner name shouldn't be less than 2 characters")
    private String ownerName;

    @NotNull(message = "address cannot be null")
    @Size(min = 2, message = "address shouldn't be less than 2 characters")
    private String address;

    @NotNull(message = "phone number cannot be null")
    @Size(min = 2, max = 15, message = "phone number must be greater than 6, less than 16 characters")
    private String phoneNumber;

    @Email
    @NotNull(message = "email cannot be null")
    private String email;

    @NotNull(message = "password cannot be null")
    @Size(min = 6, max = 16, message = "password must be greater that 6, less than 16 characters")
    private String password;

    @NotNull(message = "merchant type should be defined")
    private MerchantType type;

}
