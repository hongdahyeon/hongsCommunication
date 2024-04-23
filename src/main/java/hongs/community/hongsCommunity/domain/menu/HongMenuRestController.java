package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuInsertUpdateDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuSuperDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuSuperVo;
import hongs.community.hongsCommunity.global.hongs.dto.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/super/menu")
@RequiredArgsConstructor
public class HongMenuRestController {

    private final HongMenuService menuService;

    @GetMapping("/list.json")
    public Response list(@RequestParam(name = "menuRole", required = true) String menuRole, @RequestParam(name = "useYn", required = false) String useYn) {
        List<HongMenuSuperVo> list = menuService.superList(new HongMenuSuperDto(menuRole, useYn));
        return Response.ok(list);
    }

    @PostMapping("/insert.json")
    public Response join(@RequestBody HongMenuInsertUpdateDto dto) {
        Integer join = menuService.join(dto);
        return Response.created(join);
    }

    @PutMapping("/update.json")
    public Response update(@RequestBody HongMenuInsertUpdateDto dto) {
        Integer update = menuService.update(dto);
        return Response.ok(update);
    }

    @DeleteMapping("/delete.json")
    public Response delete(@RequestParam(name = "menuUid", required = true) Long menuUid) {
        Integer delete = menuService.delete(menuUid);
        return Response.ok(delete);
    }

}
