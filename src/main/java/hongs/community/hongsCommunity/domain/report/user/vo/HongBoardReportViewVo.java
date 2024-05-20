package hongs.community.hongsCommunity.domain.report.user.vo;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class HongBoardReportViewVo {

    private Long reportUid;
    private String title;
    private String content;
    private Long fileUid;
    private String noticeYn;
}
