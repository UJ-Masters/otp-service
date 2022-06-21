package com.masters.uj.otp.service;

import com.masters.uj.otp.exception.SomethingWentWrongException;
import com.masters.uj.otp.model.DeliveryType;
import com.masters.uj.otp.model.send.SendRequest;
import com.masters.uj.otp.model.send.SendResponse;
import com.masters.uj.otp.model.validate.ValidateRequest;
import com.masters.uj.otp.model.validate.ValidateResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Random;

@Component
public class OtpService {

    private final Logger log = LoggerFactory.getLogger(OtpService.class);
    private final HashMap<String, String> otpStorage;

    private final CommunicationService communicationService;

    @Autowired
    public OtpService(CommunicationService communicationService) {
        otpStorage = new HashMap<>();
        this.communicationService = communicationService;
    }


    private char[] generateOtp() {

        int size = 4;

        // Using numeric values
        String numbers = "0123456789";

        // Using random method
        Random random_method = new Random();

        char[] otp = new char[size];

        for (int i = 0; i < size; i++) {
            // Use of charAt() method : to get character value
            // Use of nextInt() as it is scanning the value as int
            otp[i] =
                    numbers.charAt(random_method.nextInt(numbers.length()));
        }
        return otp;
    }

    public SendResponse sendOtp(SendRequest request) {

        try {
            String otp = String.valueOf(generateOtp());
            request.setMessage(otp);
            if (request.getDeliveryType() == DeliveryType.E) {
                otpStorage.put(request.getEmail(), otp);
                return communicationService.sendEmail(request);
            }
            otpStorage.put(request.getMobileNumber(), otp);
            return communicationService.sendSms(request);

        } catch (URISyntaxException | RestClientException ex) {
            log.error(ex.getMessage());
            throw new SomethingWentWrongException(ex.getMessage());
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw ex;
        }

    }

    private boolean map(ValidateRequest request) {
        if (DeliveryType.E == request.getDeliveryType()) {
            if (otpStorage.get(request.getEmail()).equals(request.getOtp())) {
                otpStorage.remove(request.getEmail());
                return true;
            }
        }

        if (otpStorage.get(request.getMobileNumber()).equals(request.getOtp())) {
            otpStorage.remove(request.getMobileNumber());
            return true;
        }
        return false;
    }

    public ValidateResponse validateOtp(ValidateRequest request) {
        ValidateResponse response = new ValidateResponse();
        response.setResult(map(request));
        return response;
    }

}
