package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import hongs.community.hongsCommunity.global.auth.PrincipalDetails;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
public class HongCalendarRestController {

    private final HongCalendarService service;

    @GetMapping("/list.json")
    public Response list(@AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userUid = principalDetails.getUser().getUserUid();
        List<HongCalendarVo> list = service.list(userUid);
        return Response.ok(list);
    }
}
