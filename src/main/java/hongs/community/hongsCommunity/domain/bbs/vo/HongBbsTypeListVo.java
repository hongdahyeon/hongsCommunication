package hongs.community.hongsCommunity.domain.bbs.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsTypeListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 리스트 조회 vo
**/

@Getter @Setter
public class HongBbsTypeListVo {

    private Long hongBbsTypeUid;
    private String bbsTypeCd;
    private String bbsTypeNm;
    private String bbsCd;
    private String bbsNm;
    private String cmntUseYn;
    private String cmntUseYnStr;
    private String fileAtchYn;
    private String fileAtchYnStr;
    private String useYn;
    private String useYnStr;
    private String regName;
    private String regDt;
}
