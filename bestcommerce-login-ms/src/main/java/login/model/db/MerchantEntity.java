package login.model.db;

import login.util.MerchantType;
import lombok.Data;

import javax.persistence.*;

@Entity(name = "merchants")
@Data
public class MerchantEntity {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String ownerName;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(nullable = false, length = 50)
    private String phoneNumber;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String encryptedPassword;

    @Column(nullable = false, unique = true)
    private String randomId;

    @Enumerated(EnumType.STRING)
    private MerchantType type;
}
