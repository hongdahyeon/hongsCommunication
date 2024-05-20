package hongs.community.hongsCommunity.domain.report.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardReportViewVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 단건 조회 vo
**/

@Getter @Setter
public class HongBoardReportViewVo {

    private Long reportUid;
    private String title;
    private String content;
    private Long fileUid;
    private String noticeYn;
}