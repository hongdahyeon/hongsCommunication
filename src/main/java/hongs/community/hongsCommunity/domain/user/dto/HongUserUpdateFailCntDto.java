package hongs.community.hongsCommunity.domain.user.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HongUserUpdateFailCntDto {

    private String userId;
    private Integer pwdFailCnt;

    public HongUserUpdateFailCntDto(String userId, Integer pwdFailCnt) {
        this.userId = userId;
        this.pwdFailCnt = pwdFailCnt;
    }
}
