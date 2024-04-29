package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongSearchIdPwdDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserChngPwdDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.dto.HongUserSendEmailDto;
import hongs.community.hongsCommunity.domain.user.service.HongUserService;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/front/user")
@Tag(name = "유저 Front RestController", description = "로그인 이전 접근 가능한 RestController")
public class HongUserFrontRestController {

    private final HongUserService userService;
    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\." +
                    "[a-zA-Z0-9_+&*-]+)*@" +
                    "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                    "A-Z]{2,7}$";

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

    @PutMapping("/updatePwd.json")
    @Operation(summary = "비밀번호 변경하기", description = "사용자의 비밀번호가 만료되어 비밀번호를 연장 혹은 변경한다.")
    @ApiDocumentResponse
    public Response updatePwd(@RequestBody HongUserChngPwdDto dto) {
        Integer changePwd = userService.changePwd(dto);
        if(changePwd == 1) return Response.ok(changePwd);
        else return Response.badRequest();
    }

    @GetMapping("/sendEmail.json")
    @Operation(summary = "이메일로 인증번호 전송", description = "1년간 로그인을 안하여 휴먼계정이된 경우, 이메일 인증을 통해 풀어야 한다.")
    @ApiDocumentResponse
    public Response sendEmail(HongUserSendEmailDto dto) {
        Boolean sendEmail = userService.sendEmail(dto);
        String message = (sendEmail) ? "해당 이메일로 인증번호를 전송했습니다." : "해당되는 사용자가 없습니다. \n 이메일 및 아이디를 확인해주세요.";
        return (sendEmail) ? Response.ok(message) : Response.badRequest(message);
    }

    @GetMapping("/chkUserId.json")
    @Operation(summary = "아이디 중복 확인", description = "회원가입시, 아이디 중복 확인")
    @ApiDocumentResponse
    public Response chkUserId(@RequestParam(name = "userId", required = true) String userId) {
        Boolean chkedUserId = userService.chkUserId(userId);
        String message = (chkedUserId) ? "사용 가능한 아이디입니다." : "중복되는 아이디입니다. \n 다른 아이디를 입력해주세요.";
        return (chkedUserId) ? Response.ok(message) : Response.badRequest(message);
    }

    @GetMapping("/chkUserEmail.json")
    @Operation(summary = "이메일 중복 확인", description = "회원가입시, 이메일 중복 확인")
    @ApiDocumentResponse
    public Response chkUserEmail(@RequestParam(name = "userEmail", required = true) String userEmail) {
        Map<String, Object> map = new HashMap<>();
        if(isValidEmail(userEmail)) {
            Integer chkedUserEmail = userService.chkUserEmail(userEmail);
            String message = (chkedUserEmail == 0) ? "사용 가능한 이메일입니다." : "중복되는 이메일입니다. \n 다른 이메일을 입력해주세요.";
            map.put("key", chkedUserEmail); map.put("message", message);
        } else {
            map.put("key", 3); map.put("message", "이메일 형식이 유효하지 않습니다.");
        }
        return Response.ok(map);
    }

    private boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
