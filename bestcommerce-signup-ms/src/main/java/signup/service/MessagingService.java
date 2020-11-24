package signup.service;

import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import signup.model.CreateMerchantReq;
import signup.model.CreateMerchantResp;

@Service
@AllArgsConstructor
public class MessagingService {

    private final RabbitTemplate rabbitTemplate;

    public CreateMerchantResp send_and_receive_reply(CreateMerchantReq requestModel) {
        return rabbitTemplate.convertSendAndReceiveAsType("create.merchant", requestModel,
                new ParameterizedTypeReference<>() {
                });
    }
}
