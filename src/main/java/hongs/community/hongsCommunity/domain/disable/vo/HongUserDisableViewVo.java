package hongs.community.hongsCommunity.domain.disable.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongUserDisableViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-08
* @summary   비활성화된 사용자의 정보 보기 -> 비활성화 날짜 및 사유, 비활성화 한 사용자
**/

@Getter @Setter
public class HongUserDisableViewVo {

    private Long userUid;
    private String disableDate;
    private String disableMsg;
    private String mdfrName;
}
