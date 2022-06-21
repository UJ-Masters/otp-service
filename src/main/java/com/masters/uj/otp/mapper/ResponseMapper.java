package com.masters.uj.otp.mapper;

import com.masters.uj.otp.model.send.SendResponse;
import org.springframework.stereotype.Component;

@Component
public class ResponseMapper {

    public SendResponse map(boolean result) {
        SendResponse response = new SendResponse();
        response.setResult(result);
        return response;
    }

}
