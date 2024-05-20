package hongs.community.hongsCommunity.domain.report.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.FileRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardReportInsertUpdateDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-17
* @summary  게시글 저장 및 수정 dto
**/

@Getter @Setter
public class HongBoardReportInsertUpdateDto extends FileRequest {

    private Long hongBoardReportUid;
    private Long hongBoardTypeUid;
    private String reportContent;
    private String reportTitle;
    private String noticeYn;
    private Long fileUid;
}