package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.service.HongUserService;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/front/user")
public class HongUserFrontRestController {

    private final HongUserService userService;
    @PostMapping("/insert.json")
    public Response join(@RequestBody @Valid HongUserInsertDto dto) {
        Integer join = userService.join(dto);
        return Response.created(join);
    }
}
