package hongs.community.hongsCommunity.domain.user.dto;

import hongs.community.hongsCommunity.domain.user.UserRole;
import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class HongUserInsertDto {

    private Long userUid;
    private Long socialUserUid;
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String role;
    private String pwdLastUpdate = TimeUtil.daysAfter(90);
    private String lastLoginDate = TimeUtil.nowDate();

    public HongUserInsertDto(HongSocialUserInsertDto dto, String encodePassword) {
        this.socialUserUid = dto.getSocialUserUid();
        this.userId = dto.getUserId();
        this.password = encodePassword;
        this.userName = dto.getUserName();
        this.userEmail = dto.getUserEmail();
        this.role = UserRole.ROLE_USER.toString();
        this.pwdLastUpdate = TimeUtil.daysAfter(90);
        this.lastLoginDate = TimeUtil.nowDate();
    }
}
