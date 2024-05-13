package hongs.community.hongsCommunity.domain.user.after.restcontroller;

import hongs.community.hongsCommunity.domain.user.after.HongUserService;
import hongs.community.hongsCommunity.domain.user.after.dto.HongUserUnlockDto;
import hongs.community.hongsCommunity.domain.user.after.vo.HongUserListVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/admin/user")
@Tag(name = "유저 RestController", description = "admin 권한에서 접근 가능한 유저 RestController")
public class HongAdminUserRestController {

    private final HongUserService userService;

    @PutMapping("/user-unlock.json")
    @Operation(summary = "관리자 계정에서 사용자 계정 잠금 풀기", description = "관리자 계정에서 사용자 계정 잠금 풀기")
    @ApiDocumentResponse
    public Response unlock(@RequestBody HongUserUnlockDto dto) {
        Integer unlocked = userService.unlock(dto);
        if(unlocked == 1) return Response.ok();
        else return Response.badRequest();
    }

    @GetMapping("/send-expired-email.json")
    @Operation(summary = "로그인 안한지 1년이 지나 휴먼계정이 된 사용자에게 이메일 전송", description = "로그인 안한지 1년이 지나 휴먼계정이 된 사용자에게 이메일 전송")
    @ApiDocumentResponse
    public Response sendExpiredEmail(@RequestParam(required = true, name = "userUid") Long userUid) {
        userService.sendExpiredEmail(userUid);
        return Response.ok();
    }

    @GetMapping("/send-credential-expired-email.json")
    @Operation(summary = "비밀번호 변경한지 90일이 지난 사용자에게 이메일 전송", description = "비밀번호 변경한지 90일이 지난 사용자에게 이메일 전송")
    @ApiDocumentResponse
    public Response sendCredentialExpiredEmail(@RequestParam(required = true, name = "userUid") Long userUid) {
        userService.sendCredentialExpiredEmail(userUid);
        return Response.ok();
    }

    @GetMapping("/list.json")
    @Operation(summary = "회원정보 리스트 조회", description = "회원정보 리스트 조회")
    @ApiDocumentResponse
    public Response list() {
        List<HongUserListVo> list = userService.list();
        return Response.ok(list);
    }
}
