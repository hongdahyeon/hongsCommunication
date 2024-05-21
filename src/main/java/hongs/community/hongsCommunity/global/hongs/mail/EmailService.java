package hongs.community.hongsCommunity.global.hongs.mail;

import hongs.community.hongsCommunity.domain.cert.HongCertCdService;
import hongs.community.hongsCommunity.domain.cert.dto.HongCertCdInsertDto;
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
    private final HongCertCdService certCdService;

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
        String certCode = StringUtil.random(6);
        String text = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <div class=\"form-group\">\n" +
                "            <label for=\"telephone\">인증번호입니다.</label>\n" +
                "            <input type=\"text\" class=\"form-control\" value=\"" + certCode + "\" readonly>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";
        certCdService.join(new HongCertCdInsertDto(to, certCode));
        this.sendEmail(to, subject, text);
    }

    public void sendExpiredEmail(String to){
        String subject = "휴먼 계정이 되었습니다.";
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <span style=\"font-size: 12px;\">" +
                "           최근 로그인 시점으로부터 1년이 지났습니다. 로그인 하셔서 휴먼 계정을 풀어주세요.\n" +
                "        </span>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        this.sendEmail(to, subject, content);
    }

    public void sendCredentialExpiredEmail(String to){
        String subject = "비밀번호를 변경 안하신지 90일이 지났습니다.";
        String content = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<body>\n" +
                "    <div class=\"container\">\n" +
                "        <span style=\"font-size: 12px;\">" +
                "           비밀번호를 변경 안하신지 90일이 지났습니다. 로그인 하셔서 비밀번호를 변경 혹은 90일 연장해주세요.\n" +
                "        </span>\n" +
                "    </div>\n" +
                "</body>\n" +
                "</html>";

        this.sendEmail(to, subject, content);
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
