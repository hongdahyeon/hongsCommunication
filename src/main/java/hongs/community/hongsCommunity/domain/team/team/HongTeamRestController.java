package hongs.community.hongsCommunity.domain.team.team;

import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamInsertDto;
import hongs.community.hongsCommunity.domain.team.team.dto.HongTeamUserInsertDto;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import hongs.community.hongsCommunity.global.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/team")
@Tag(name = "팀 RestController", description = "팀 RestController")
public class HongTeamRestController {

    private final HongTeamService teamService;

    @PostMapping("/insert.json")
    @Operation(summary = "팀 생성하기", description = "팀 생성시 -> 기본적으로 팀 대표도 team-user테이블에 승인 상태로 저장된다.")
    @ApiDocumentResponse
    public Response insert(@RequestBody HongTeamInsertDto dto) {
        Integer insertTeam = teamService.insertTeam(dto);
        return Response.created(insertTeam);
    }

    @PostMapping("/join-team.json")
    @Operation(summary = "팀 가입 신청하기", description = "팀에 가입 신청을 할 수 있다.")
    @ApiDocumentResponse
    public Response joinTeam(@RequestBody HongTeamUserInsertDto dto){
        dto.setUserUid(UserUtil.getLoginUser().getUserUid());
        Integer insertTeamUser = teamService.insertTeamUser(dto);
        return Response.created(insertTeamUser);
    }

}
