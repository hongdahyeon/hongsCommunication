package hongs.community.hongsCommunity.domain.board.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardTypeListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-16
* @summary  게시판 유형 리스트 조회 vo
**/

@Getter @Setter
public class HongBoardTypeListVo {

    private Long hongBoardTypeUid;
    private String boardTypeCd;
    private String boardTypeName;
    private String boardName;
    private String commentYn;
    private String commentYnStr;
    private String fileAtchYn;
    private String fileAtchYnStr;
    private String useYn;
    private String useYnStr;
    private String regName;
    private String regDt;
}
