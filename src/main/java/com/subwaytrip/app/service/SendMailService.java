package com.subwaytrip.app.service;

import javax.mail.MessagingException;

public interface SendMailService {

    boolean send(String email, String certCode) throws MessagingException;

}
