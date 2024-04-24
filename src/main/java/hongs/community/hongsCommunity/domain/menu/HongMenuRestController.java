package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuInsertUpdateDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuSuperDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuSuperVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.ApiDocumentResponse;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
* @fileName HongMenuRestController
* @author dahyeon
* @version 1.0.0
* @date 2024-04-24
* @summary  "/api/super/menu/**" : super 권한의 사용자만 접근 가능
**/

@RestController
@RequestMapping("api/super/menu")
@RequiredArgsConstructor
@Tag(name = "SUPER 권한: 메뉴 RestController", description = "SUPER 권한에서 접근이 가능하며, 메뉴는 권한별로 구분된다.")
public class HongMenuRestController {

    private final HongMenuService menuService;

    @GetMapping("/list.json")
    @Operation(summary = "메뉴 리스트", description = "사용자 권한별 메뉴 리스트를 가져온다.")
    @ApiDocumentResponse
    public Response list(HongMenuSuperDto dto) {
        List<HongMenuSuperVo> list = menuService.superList(dto);
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    @Operation(summary = "메뉴 추가", description = "사용자 권한별 메뉴를 추가한다.")
    @ApiDocumentResponse
    public Response join(@RequestBody HongMenuInsertUpdateDto dto) {
        Integer join = menuService.join(dto);
        return Response.created(join);
    }

    @PutMapping("/update.json")
    @Operation(summary = "메뉴 수정", description = "사용자 권한별 메뉴를 수정한다.")
    @ApiDocumentResponse
    public Response update(@RequestBody HongMenuInsertUpdateDto dto) {
        Integer update = menuService.update(dto);
        return Response.ok(update);
    }

    @DeleteMapping("/delete.json")
    @Operation(summary = "메뉴 삭제", description = "사용자 권한별 메뉴를 삭제한다.(실제로 삭제x, deleteYn 변경)")
    @ApiDocumentResponse
    public Response delete(@RequestParam(name = "menuUid", required = true) Long menuUid) {
        Integer delete = menuService.delete(menuUid);
        return Response.ok(delete);
    }

}
