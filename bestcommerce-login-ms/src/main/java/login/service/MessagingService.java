package login.service;

import login.model.CreateMerchantReq;
import login.model.CreateMerchantResp;
import login.model.db.MerchantEntity;
import login.repo.MerchantRepository;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
@Log4j2
public class MessagingService {

    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final MerchantRepository merchantRepo;

    @RabbitListener(queues = {"create.merchant"})
    public CreateMerchantResp receive_save_reply(CreateMerchantReq requestModel) {
        log.info("received object: {}", requestModel);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        MerchantEntity merchantEntity = modelMapper.map(requestModel, MerchantEntity.class);
        merchantEntity.setEncryptedPassword(passwordEncoder.encode(requestModel.getPassword()));
        merchantEntity.setRandomId(UUID.randomUUID().toString());

        try {
            MerchantEntity saved = merchantRepo.save(merchantEntity);
            CreateMerchantResp resp = modelMapper.map(saved, CreateMerchantResp.class);
            log.info("returned object: {}", resp);
            return resp;
        } catch (Exception exc) {
            log.error("exc : " + exc.getLocalizedMessage());
            return null;
        }
    }
}
