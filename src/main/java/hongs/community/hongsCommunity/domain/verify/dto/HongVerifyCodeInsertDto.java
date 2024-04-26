package hongs.community.hongsCommunity.domain.verify.dto;

import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
* @fileName HongVerifyCodeInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-26
* @summary 사용자의 이메일과 함께 사용자에게 전송된 인증번호를 저장한다.
**/

@Getter @Setter
public class HongVerifyCodeInsertDto {

    private String userEmail;
    private String verifyCode;
    private LocalDateTime verifyExpire;
    private LocalDateTime verifyCreated;
    private Boolean verifyCheck;

    public HongVerifyCodeInsertDto(String userEmail, String verifyCode) {
        this.userEmail = userEmail;
        this.verifyCode = verifyCode;
        this.verifyExpire = TimeUtil.daysAfter_Date(1);
        this.verifyCreated = LocalDateTime.now();
        this.verifyCheck = false;
    }
}
