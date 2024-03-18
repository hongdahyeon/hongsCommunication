package hongs.community.hongsCommunity.domain.user.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongCheckUserVo {

    private String userId;
    private Integer pwdFailCnt;
}
