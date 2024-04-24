package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.service.HongUserService;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
