package com.masters.uj.otp.rest;

import com.masters.uj.otp.model.send.SendRequest;
import com.masters.uj.otp.model.send.SendResponse;
import com.masters.uj.otp.model.validate.ValidateRequest;
import com.masters.uj.otp.model.validate.ValidateResponse;
import com.masters.uj.otp.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin({"*"})
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }


    @PostMapping("/send")
    @ResponseBody
    public SendResponse sendOtp(@RequestBody SendRequest request) {
        return otpService.sendOtp(request);
    }

    @PostMapping("/validate")
    @ResponseBody
    public ValidateResponse validateOtp(HttpServletRequest servletRequest, @RequestBody ValidateRequest request) {
        boolean override = Boolean.parseBoolean(servletRequest.getHeader("Override"));
        return otpService.validateOtp(request, override);
    }

}
