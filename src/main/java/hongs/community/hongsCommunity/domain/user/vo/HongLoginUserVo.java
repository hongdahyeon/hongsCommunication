package hongs.community.hongsCommunity.domain.user.vo;

import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class HongLoginUserVo {

    private Long userUid;
    private Long socialUserUid;
    private String userId;
    private String password;
    private String userName;
    private String userEmail;
    private String role;
    private Boolean isEnable;
    private String pwdLastUpdate;
    private String lastLoginDate;
    private Boolean isNonLocked;

    private List<HongMenuVo> menu = new ArrayList<>();
}
