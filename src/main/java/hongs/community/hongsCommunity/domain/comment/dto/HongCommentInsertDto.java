package hongs.community.hongsCommunity.domain.comment.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCommentInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 등록용 dto
**/

@Getter @Setter
public class HongCommentInsertDto extends CreateRequest {

    private String commentCnt;
    private String targetType;
    private Long targetId;
}
