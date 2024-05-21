package hongs.community.hongsCommunity.domain.cmnt.dto;

import lombok.Getter;
import lombok.Setter;


/**
* @fileName HongCmntListDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 리스트 조회 dto
**/

@Getter @Setter
public class HongCmntListDto {

    private String type;        // 댓글 부모 type (어느 타입의 글에 달린 댓글인가)
    private Long targetUid;     // 댓글 부모 uid (어느 글에 달린 댓글인가)
    private Long loginUser;
}
