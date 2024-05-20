package hongs.community.hongsCommunity.domain.report.admin.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongAdminBoardReportListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 리스트 조회 vo
**/

@Getter @Setter
public class HongAdminBoardReportListVo {

    private Long reportUid;
    private Long typeUid;
    private String title;
    private String content;
    private Long fileUid;
    private Integer fileCnt;
    private Integer commentCnt;
    private String noticeYn;
    private String regName;
    private String regDt;
}
