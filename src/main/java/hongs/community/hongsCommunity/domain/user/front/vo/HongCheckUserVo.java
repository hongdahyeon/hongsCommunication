package hongs.community.hongsCommunity.domain.user.front.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCheckUserVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-03
* @summary  사용자 비밀번호 실패횟수 리턴 vo
**/

@Getter @Setter
public class HongCheckUserVo {

    private String userId;
    private Integer pwdFailCnt;
}
