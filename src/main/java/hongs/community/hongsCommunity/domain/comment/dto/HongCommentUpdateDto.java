package hongs.community.hongsCommunity.domain.comment.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCommentUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 수정용 dto
**/

@Getter @Setter
public class HongCommentUpdateDto extends UpdateRequest {

    private String commentCnt;
    private Long commentUid;
}
