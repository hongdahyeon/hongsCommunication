package hongs.community.hongsCommunity.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongLoginUserVo {

    private Long userUid;
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String role;
    private Boolean isEnable;
    private String pwdLastUpdate;
    private String lastLoginDate;
    private Boolean isNonLocked;
}
