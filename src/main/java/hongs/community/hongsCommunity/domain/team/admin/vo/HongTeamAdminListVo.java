package hongs.community.hongsCommunity.domain.team.admin.vo;


import lombok.Getter;
import lombok.Setter;

/**
* @fileName HongTeamAdminListVo
* @author dahyeon
* @version 1.0.0
* @date 2024-05-13
* @summary  팀 관리 : 팀 정보 리스트 조회
**/

@Getter @Setter
public class HongTeamAdminListVo {

    private Long hongTeamUid;
    private String teamCategory;
    private String teamCategoryName;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private Long teamProfile;
    private Long representId;
    private String representName;
    private String approvalYn;
    private String approvalStr;
    private String useYn;
    private String useStr;
    private String deletYn;
    private Integer totalMemberNum;
}
