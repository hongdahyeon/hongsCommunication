package hongs.community.hongsCommunity.domain.postAprs.dto;

import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
* @fileName HongBbsPstAprsListDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-20
* @summary  게시글에 등록된 사용자의 감정 조회
 *          -> regChkIf == "true" : 게시글에 등록한 '특정 사용자'의 감정 개수
 *          -> regChkIf == "false" : 게시글에 등록한 '총' 감정 개수
**/

@Getter @Setter
@NoArgsConstructor
public class HongBbsPstAprsListDto {

    private Long bbsPstUid;
    private String aprsCd;
    private Long regId;
    private String regChkTf;

    public HongBbsPstAprsListDto(Long bbsPstUid, String aprsCd, String regChkTf) {
        this.bbsPstUid = bbsPstUid;
        this.aprsCd = aprsCd;
        this.regId = UserUtil.getLoginUserUid();
        this.regChkTf = regChkTf;
    }
}
