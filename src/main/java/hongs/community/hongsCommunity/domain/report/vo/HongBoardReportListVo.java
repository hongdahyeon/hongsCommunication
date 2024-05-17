package hongs.community.hongsCommunity.domain.report.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongBoardReportListVo {

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
