package com.controller;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


 
public class SMTPAuthenticatior extends Authenticator{
 
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("이메일아이디","비밀번호");
    }
}
