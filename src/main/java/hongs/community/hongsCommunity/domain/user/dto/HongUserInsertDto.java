package hongs.community.hongsCommunity.domain.user.dto;

import hongs.community.hongsCommunity.global.util.TimeUtil;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongUserInsertDto {

    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String role;
    private String pwdLastUpdate = TimeUtil.daysAfter(90);
    private String lastLoginDate = TimeUtil.nowDate();
}
