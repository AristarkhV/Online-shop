package service;

import model.Code;

public interface MailService {

    void sendConfirmCode(Code code, String email);
}
