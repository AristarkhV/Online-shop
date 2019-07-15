package service.serviceImpl;

import service.MailService;

public class MailServiceImpl implements MailService {
//    @Override
//    public void sendConfirmCode(Code code) {
//        final String userName = "u.kiyanitsa@gmail.com";
//        final String password = "Makar4yk";
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true");
//
//        Session session = Session.getInstance(prop,
//                new Authenticator() {
//                    @Override
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(userName, password);
//                    }
//                });
//        try{
//            Message message = new MineMessage(session);
//            message.setFrom(new InternetAddress(userName));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse(code.getUser().getEmail())
//        };
//        message.setSubject("Одноразовый код для подтверждения покупки");
//        message.setText(RandomHelper.get4DigitCode());
//
//
//    }
}
