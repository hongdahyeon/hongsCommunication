package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.calendar.dto.HongCalendarDto;
import hongs.community.hongsCommunity.domain.calendar.vo.HongCalendarVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import hongs.community.hongsCommunity.global.util.UserUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/calendar")
@Tag(name = "캘린더 RestController", description = "로그인 사용자별로 자신의 캘린더 정보가 보여진다.")
public class HongCalendarRestController {

    private final HongCalendarService service;

    @GetMapping("/list.json")
    @Operation(summary = "캘린더 리스트", description = "로그인한 사용자의 캘린더 리스트 정보를 가져온다.")
    @ApiDocumentResponse
    public Response list() {
        Long userUid = UserUtil.getLoginUser().getUserUid();
        List<HongCalendarVo> list = service.list(userUid);
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "일정 저장", description = "로그인한 사용자에 대해 일정 정보를 저장한다.")
    @ApiDocumentResponse
    public Response join(@RequestBody HongCalendarDto dto){
        Long userUid = UserUtil.getLoginUser().getUserUid();
        dto.setUserUid(userUid);
        Long joinId = service.join(dto);
        return Response.created(joinId);
    }

    @PutMapping("/update.json")
    @Operation(summary = "일정 수정", description = "로그인한 사용자에 대해 일정 정보를 수정한다.")
    @ApiDocumentResponse
    public Response update(@RequestBody HongCalendarDto dto){
        int update = service.update(dto);
        return Response.ok(update);
    }

    @PutMapping("/updateDate.json")
    @Operation(summary = "일정 날짜 수정", description = "로그인한 사용자에 대해 일정의 날짜정보를 수정한다.")
    @ApiDocumentResponse
    public Response updateDate(@RequestBody HongCalendarDto dto){
        int updateDate = service.updateDate(dto);
        return Response.ok(updateDate);
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "일정 삭제", description = "로그인한 사용자에 대해 일정 정보를 삭제한다.(실제로 삭제)")
    @ApiDocumentResponse
    public Response delete(@RequestParam(required = true, name = "clUid") Long calUid) {
        int delete = service.delete(calUid);
        return Response.ok(delete);
    }
}
