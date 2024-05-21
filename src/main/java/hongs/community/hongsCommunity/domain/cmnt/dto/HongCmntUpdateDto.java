package hongs.community.hongsCommunity.domain.cmnt.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongCmntUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  댓글 수정용 dto
**/

@Getter @Setter
public class HongCmntUpdateDto extends UpdateRequest {

    private String cmntCn;
    private Long hongCmntUid;
}
