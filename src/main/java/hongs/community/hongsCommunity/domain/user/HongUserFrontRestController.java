package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongSearchIdPwdDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.service.HongUserService;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/front/user")
@Tag(name = "유저 Front RestController", description = "로그인 이전 접근 가능한 RestController")
public class HongUserFrontRestController {

    private final HongUserService userService;
    @PostMapping("/insert.json")
    @Operation(summary = "회원가입", description = "사용자 회원가입을 한다.")
    @ApiDocumentResponse
    public Response join(@RequestBody @Valid HongUserInsertDto dto) {
        Integer join = userService.join(dto);
        return Response.created(join);
    }

    @GetMapping("/searchId.json")
    @Operation(summary = "이메일로 아이디 전송", description = "사용자의 이메일과 이름을 통해 아이디를 가져와 해당 이메일로 아이디를 전송한다.")
    @ApiDocumentResponse
    public Response searchId(HongSearchIdPwdDto dto){
        Boolean searchId = userService.searchId(dto);
        if(searchId) return Response.ok("이메일로 아이디를 전송했습니다.");
        else return Response.badRequest("사용자를 찾지 못했습니다. 다시 한번 확인해주세요.");
    }

    @GetMapping("/searchPwd.json")
    @Operation(summary = "이메일로 초기화된 비밀번호 전송", description = "사용자의 이메일과 이름을 통해 사용자가 있는지 찾고, 해당 사용자의 비밀번호를 초기화해 전송한다.")
    @ApiDocumentResponse
    public Response searchPwd(HongSearchIdPwdDto dto){
        Boolean searchPwd = userService.searchPwd(dto);
        if(searchPwd) return Response.ok("이메일로 초기화된 비밀번호를 전송했습니다.");
        else return Response.badRequest("사용자를 찾지 못했습니다. 다시 한번 확인해주세요.");
    }

}
