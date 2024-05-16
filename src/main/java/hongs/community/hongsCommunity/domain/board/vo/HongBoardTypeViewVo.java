package hongs.community.hongsCommunity.domain.board.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardTypeViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 단건 조회 vo
**/

@Getter @Setter
public class HongBoardTypeViewVo {

    private Long hongBoardTypeUid;
    private String boardTypeCd;
    private String boardName;
    private String commentYn;
    private String fileAtchYn;
    private String useYn;
}
