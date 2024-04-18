package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.dto.HongCalendarDto;
import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import hongs.community.hongsCommunity.global.auth.PrincipalDetails;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/insert.json")
    public Response join(@RequestBody HongCalendarDto dto, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long userUid = principalDetails.getUser().getUserUid();
        dto.setUserUid(userUid);
        Long joinId = service.join(dto);
        return Response.created(joinId);
    }

    @PutMapping("/update.json")
    public Response update(@RequestBody HongCalendarDto dto){
        int update = service.update(dto);
        return Response.ok(update);
    }

    @PutMapping("/updateDate.json")
    public Response updateDate(@RequestBody HongCalendarDto dto){
        int updateDate = service.updateDate(dto);
        return Response.ok(updateDate);
    }

    @DeleteMapping("/delete.json")
    public Response delete(@RequestParam(required = true, name = "clUid") Long calUid) {
        int delete = service.delete(calUid);
        return Response.ok(delete);
    }
}
