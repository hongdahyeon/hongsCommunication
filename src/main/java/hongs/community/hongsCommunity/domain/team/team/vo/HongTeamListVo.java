package hongs.community.hongsCommunity.domain.team.team.vo;

import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-14
* @summary  팀 리스트 조회 vo
**/

@Getter @Setter
public class HongTeamListVo {

    private Long hongTeamUid;
    private String teamCategory;
    private String teamCategoryName;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private Long teamProfile;
    private String teamProfileUrl;
    private Long representId;
    private String representName;
    private String approvalYn;
    private String useYn;
    private String deleteYn;
    private Integer totalMemberNum;
    private String joinedStatus;
    private Boolean ifJoined;
}
