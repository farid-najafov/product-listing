package signup.controller;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import signup.model.CreateMerchantReq;
import signup.model.CreateMerchantResp;
import signup.service.MessagingService;

import javax.validation.Valid;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/signup")
public class MerchantController {

    private final MessagingService messagingService;

    @PostMapping
    public ResponseEntity<?> createMerchant(@Valid @RequestBody CreateMerchantReq requestModel) {

        CreateMerchantResp reply = messagingService.send_and_receive_reply(requestModel);
        log.info("received object: {}", reply);

        return reply != null ?
                ResponseEntity.status(HttpStatus.CREATED).body(reply) :
                ResponseEntity.badRequest().body(new CreateMerchantResp());
    }

}
