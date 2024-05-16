package hongs.community.hongsCommunity.domain.board.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardTypeInsertUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 등록 및 수정 dto
**/

@Getter @Setter
public class HongBoardTypeInsertUpdateDto extends CreateRequest {

    private Long hongBoardTypeUid;
    private String boardName;
    private String boardTypeCd;
    private String commentYn;
    private String fileAtchYn;
    private String useYn;

}
