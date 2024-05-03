package hongs.community.hongsCommunity.domain.user.front.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
* @fileName HongUserUpdateFailCntDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  비밀번호 틀린 횟수 업데이트
**/

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
