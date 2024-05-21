package hongs.community.hongsCommunity.domain.post.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsPostListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 리스트 조회 vo
**/

@Getter @Setter
public class HongBbsPostListVo {

    private Long pstUid;
    private Long typeUid;
    private String pstTtl;
    private String pstCn;
    private Long fileUid;
    private Integer fileCnt;
    private Integer commentCnt;
    private Integer likeCnt;
    private Integer disLikeCnt;
    private String ntcYn;
    private String regName;
    private String regDt;
}
