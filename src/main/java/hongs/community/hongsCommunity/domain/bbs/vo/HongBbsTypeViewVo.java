package hongs.community.hongsCommunity.domain.bbs.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsTypeViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 단건 조회 vo
**/

@Getter @Setter
public class HongBbsTypeViewVo {

    private Long hongBbsTypeUid;
    private String bbsTypeCd;
    private String bbsNm;
    private String cmntUseYn;
    private String fileAtchYn;
    private String useYn;
}
