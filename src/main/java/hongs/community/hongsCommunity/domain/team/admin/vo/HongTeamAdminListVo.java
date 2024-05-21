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
    private String teamCtgry;
    private String teamCtgryNm;
    private String teamNm;
    private Integer memberNum;
    private String teamShortIntro;
    private Long teamProfile;
    private Long rprsvUid;              // 대표자 uid
    private String rprsvNm;           // 대표자명
    private String aprvYn;              // 승인여부
    private String aprvYnStr;           // 승인여부 str
    private String useYn;
    private String useStr;
    private String delYn;
    private Integer totalMemberNum;
}
