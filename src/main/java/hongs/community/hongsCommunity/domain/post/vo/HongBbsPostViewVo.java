package hongs.community.hongsCommunity.domain.post.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsPostViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 단건 조회 vo
**/

@Getter @Setter
public class HongBbsPostViewVo {

    private Long pstUid;
    private String pstTtl;
    private String pstCn;
    private Long fileUid;
    private String ntcYn;
}
