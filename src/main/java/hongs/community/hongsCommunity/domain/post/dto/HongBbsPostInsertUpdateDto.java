package hongs.community.hongsCommunity.domain.post.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.FileRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsPostInsertUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 저장 및 수정 dto
**/

@Getter @Setter
public class HongBbsPostInsertUpdateDto extends FileRequest {

    private Long hongBbsPstUid;
    private Long hongBbsTypeUid;
    private String pstTtl;
    private String pstCn;
    private String ntcYn;
    private Long fileUid;
}
