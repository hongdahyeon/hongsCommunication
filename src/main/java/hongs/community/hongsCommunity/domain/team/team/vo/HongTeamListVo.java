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
    private String teamCtgry;
    private String teamCtgryNm;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private Long teamProfile;
    private String teamProfileUrl;
    private Long rprsvUid;              // 대표자 uid
    private String rprsvNm;             // 대표자명
    private String aprvYn;              // 승인여부
    private String useYn;
    private String delYn;
    private Integer totalMemberNum;
    private String joinedStatus;
    private Boolean ifJoined;
}
