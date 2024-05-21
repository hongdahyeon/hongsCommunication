package hongs.community.hongsCommunity.domain.team.team.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.FileRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 수정 dto
**/

@Getter @Setter
public class HongTeamUpdateDto extends FileRequest {

    private Long hongTeamUid;
    private String teamCtgry;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private String teamIntro;
    private Long teamProfile;
    private Long rprsvUid;
}
