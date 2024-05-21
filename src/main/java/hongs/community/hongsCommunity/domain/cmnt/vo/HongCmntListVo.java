package hongs.community.hongsCommunity.domain.cmnt.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCmntListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 list 조회 vo
**/

@Getter @Setter
public class HongCmntListVo {

    private Long hongCmntUid;
    private String cmntCn;
    private Boolean canEdit;
    private String userNm;
    private String userEmail;
    private String profileSrc;

}
