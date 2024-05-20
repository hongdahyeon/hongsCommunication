package hongs.community.hongsCommunity.domain.user.front.dto;

import hongs.community.hongsCommunity.domain.user.HongUserRole;
import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongUserInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  회원가입
**/

@Getter @Setter @NoArgsConstructor
public class HongUserInsertDto {

    private Long userUid;
    private Long socialUserUid;
    private String userId;
    private String password;
    private String userNm;
    private String userEmail;
    private String role;
    private String pwdLastUpdate = TimeUtil.daysAfter(90);
    private String lastLoginDate = TimeUtil.nowDate();

    public HongUserInsertDto(HongSocialUserInsertDto dto, String encodePassword) {
        this.socialUserUid = dto.getSocialUserUid();
        this.userId = dto.getUserId();
        this.password = encodePassword;
        this.userNm = dto.getUserNm();
        this.userEmail = dto.getUserEmail();
        this.role = HongUserRole.ROLE_USER.toString();
        this.pwdLastUpdate = TimeUtil.daysAfter(90);
        this.lastLoginDate = TimeUtil.nowDate();
    }
}
