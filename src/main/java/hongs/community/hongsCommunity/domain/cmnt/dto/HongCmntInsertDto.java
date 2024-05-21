package hongs.community.hongsCommunity.domain.cmnt.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCmntInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 등록용 dto
**/

@Getter @Setter
public class HongCmntInsertDto extends CreateRequest {

    private String cmntCn;
    private String targetType;
    private Long targetUid;
}
