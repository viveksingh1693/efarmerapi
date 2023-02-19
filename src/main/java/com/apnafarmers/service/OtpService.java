package com.apnafarmers.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.EmailDTO;

import lombok.extern.slf4j.Slf4j;

@Description(value = "Service responsible for handling OTP related functionality.")
@Service
@Slf4j
public class OtpService {


    private OtpGenerator otpGenerator;
    private EmailService emailService;
    private UserService userService;


    public OtpService(OtpGenerator otpGenerator, EmailService emailService, UserService userService)
    {
        this.otpGenerator = otpGenerator;
        this.emailService = emailService;
        this.userService = userService;
    }

    /**
     * Method for generate OTP number
     *
     * @param key - provided key (username in this case)
     * @return boolean value (true|false)
     */
    public Boolean generateOtp(String key)
    {
        // generate otp
        Integer otpValue = otpGenerator.generateOTP(key);
        if (otpValue == -1)
        {
            log.error("OTP generator is not working...");
            return  false;
        }

        log.info("Generated OTP: {}", otpValue);

        // fetch user e-mail from database
        String userEmail = userService.findEmailByUsername(key);
        List<String> recipients = new ArrayList<>();
        recipients.add(userEmail);

        // generate emailDTO object
        EmailDTO emailDTO = new EmailDTO();
        emailDTO.setSubject("Spring Boot OTP Password.");
        emailDTO.setBody("OTP Password: " + otpValue);
        emailDTO.setRecipients(recipients);

        // send generated e-mail
        return emailService.sendSimpleMessage(emailDTO);
    }

    /**
     * Method for validating provided OTP
     *
     * @param key - provided key
     * @param otpNumber - provided OTP number
     * @return boolean value (true|false)
     */
    public Boolean validateOTP(String key, Integer otpNumber)
    {
        // get OTP from cache
        Integer cacheOTP = otpGenerator.getOPTByKey(key);
        if (cacheOTP!=null && cacheOTP.equals(otpNumber))
        {
            otpGenerator.clearOTPFromCache(key);
            return true;
        }
        return false;
    }
}
