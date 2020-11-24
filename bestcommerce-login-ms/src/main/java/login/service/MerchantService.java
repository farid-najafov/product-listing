package login.service;

import login.model.db.MerchantEntity;
import login.repo.MerchantRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class MerchantService implements UserDetailsService {

    private final MerchantRepository merchantRepo;

    private static User mapper_to_user(MerchantEntity merchantEntity) {
        return new User(
                merchantEntity.getEmail(),
                merchantEntity.getEncryptedPassword(),
                true,
                true,
                true,
                true,
                new ArrayList<>()
        );
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return merchantRepo.findByEmail(email)
                .map(MerchantService::mapper_to_user)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB with that email", email)
                ));
    }

    public MerchantEntity findByEmail(String email) {
        return merchantRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB with that email", email)
                ));
    }

    public UserDetails loadUserById(String randomId) {
        return merchantRepo.findByRandomId(randomId)
                .map(MerchantService::mapper_to_user)
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB with that id", randomId)
                ));
    }

    public MerchantEntity findMerchantById(String id) {
        return merchantRepo.findById(Long.parseLong(id))
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User: %s isn't found in our DB with that id", id)
                ));
    }
}
