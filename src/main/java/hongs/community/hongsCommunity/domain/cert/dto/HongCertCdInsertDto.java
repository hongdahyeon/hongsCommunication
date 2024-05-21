package hongs.community.hongsCommunity.domain.cert.dto;

import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
* @fileName HongCertCdInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-04-26
* @summary 사용자의 이메일과 함께 사용자에게 전송된 인증번호를 저장한다.
**/

@Getter @Setter
public class HongCertCdInsertDto {

    private String userEmail;
    private String certCd;
    private LocalDateTime certCdExpryYmd;
    private LocalDateTime certCdCrtYmd;
    private Boolean certCdUseYn;

    public HongCertCdInsertDto(String userEmail, String certCd) {
        this.userEmail = userEmail;
        this.certCd = certCd;
        this.certCdExpryYmd = TimeUtil.daysAfter_Date(1);
        this.certCdCrtYmd = LocalDateTime.now();
        this.certCdUseYn = false;
    }
}
