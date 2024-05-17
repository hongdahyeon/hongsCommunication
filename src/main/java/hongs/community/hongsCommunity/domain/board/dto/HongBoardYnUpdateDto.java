package hongs.community.hongsCommunity.domain.board.dto;


import hongs.community.hongsCommunity.global.hongs.dto.request.UpdateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardYnUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시판 유형 관리 yn value 수정 dto
**/

@Getter @Setter
public class HongBoardYnUpdateDto extends UpdateRequest {

    private Long uid;
    private String type;
    private String ynValue;
}
