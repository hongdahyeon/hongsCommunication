package hongs.community.hongsCommunity.domain.team.team.dto;

import hongs.community.hongsCommunity.global.hongs.dto.request.FileRequest;
import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamInsertDto
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 생성 dto
**/

@Getter @Setter
public class HongTeamInsertDto extends FileRequest {

    private Long hongTeamUid;
    private String teamCategory;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private String teamIntro;
    private Long teamProfile;
    private Long representId;
}
