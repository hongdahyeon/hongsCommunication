package hongs.community.hongsCommunity.global.hongs.mail;

import hongs.community.hongsCommunity.domain.verify.HongVerifyCodeService;
import hongs.community.hongsCommunity.domain.verify.dto.HongVerifyCodeInsertDto;
import hongs.community.hongsCommunity.global.util.StringUtil;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;
    private final HongVerifyCodeService verifyCodeService;

    public void sendTest(String to) {
        String subject = "이메일 전송 테스트";
        String content = "<b> 이메일 전송 테스트입니다. </b>";
        this.sendEmail(to, subject, content);
    }

    public void sendSearchId(String to, String searchId) {
        String subject = "아이디입니다.";
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"telephone\">아이디입니다.</label>\n" +
                "            <input type=\"text\" class=\"form-control\" value=\"" + searchId + "\" readonly>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        this.sendEmail(to, subject, content);
    }

    public void sendInitialPwdEmail(String to, String initialPassword){
        String subject = "초기화된 비밀번호입니다.";
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"telephone\">초기화된 비밀번호입니다</label>\n" +
                "            <input type=\"text\" class=\"form-control\" value=\"" + initialPassword + "\" readonly>\n" +
                "        </div>\n" +
                "        <span style=\"color: red; font-size: 12px;\">로그인 후\n" +
                "            <span style=\"text-decoration: underline;\">\"프로필 이미지 클릭 > 나의정보\"</span>\n" +
                "            에서 비밀번호를 변경해주시기 바랍니다.\n" +
                "        </span>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        this.sendEmail(to, subject, text);
    }

    public void sendVerification(String to) {
        String subject = "이메일 인증번호입니다.";
        String verifyCode = StringUtil.random(6);
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"telephone\">인증번호입니다.</label>\n" +
                "            <input type=\"text\" class=\"form-control\" value=\"" + verifyCode + "\" readonly>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        verifyCodeService.join(new HongVerifyCodeInsertDto(to, verifyCode));
        this.sendEmail(to, subject, text);
    }

    private void sendEmail(String to, String subject, String content) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);
            javaMailSender.send(message);
        }catch(MessagingException e) {
            e.printStackTrace();
        }

    }
}
