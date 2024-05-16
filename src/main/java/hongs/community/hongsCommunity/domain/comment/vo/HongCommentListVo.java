package hongs.community.hongsCommunity.domain.comment.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCommentListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 list 조회 vo
**/

@Getter @Setter
public class HongCommentListVo {

    private Long hongCommentUid;
    private String commentCnt;
    private Boolean canEdit;
    private String userName;
    private String userEmail;
    private String profileSrc;

}
