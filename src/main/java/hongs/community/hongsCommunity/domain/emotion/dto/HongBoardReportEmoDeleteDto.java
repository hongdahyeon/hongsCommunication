package hongs.community.hongsCommunity.domain.emotion.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBoardReportEmoDeleteDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-20
* @summary  게시글에 등록된 사용자의 감정 삭제
**/

@Getter @Setter
public class HongBoardReportEmoDeleteDto extends CreateRequest {
    private Long boardReportUid;
    private String emotionCode;
}
