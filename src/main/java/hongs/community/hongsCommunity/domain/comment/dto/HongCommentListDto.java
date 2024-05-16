package hongs.community.hongsCommunity.domain.comment.dto;

import lombok.Getter;
import lombok.Setter;


/**
* @fileName HongCommentListDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 리스트 조회 dto
**/

@Getter @Setter
public class HongCommentListDto {

    private String type;
    private Long targetId;
    private Long loginUser;
}
