package hongs.community.hongsCommunity.domain.postAprs.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.CreateRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongBbsPstAprsInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-20
* @summary  게시글에 감정 등록하기
**/

@Getter @Setter
public class HongBbsPstAprsInsertDto extends CreateRequest {
    private Long bbsPstUid;
    private String aprsCd;
}
