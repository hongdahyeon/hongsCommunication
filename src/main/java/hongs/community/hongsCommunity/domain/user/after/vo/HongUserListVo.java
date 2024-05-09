package hongs.community.hongsCommunity.domain.user.after.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongUserListVo {

    private Long userUid;
    private String userId;
    private String userName;
    private String userEmail;
    private String roleName;
    private Boolean isEnable;
    private String pwdLastUpdate;
    private String lastLoginDate;
    private Boolean isUserExpired;
    private Boolean isCredentialExpired;
    private Boolean isNonLocked;
    private Long socialUserUid;
}