package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.board.HongBoardTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("admin/board/report")
@Controller
public class HongBoardReportController {

    private final HongBoardTypeService boardTypeService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, @RequestParam(name = "uid", required = false) Long uid, Model model) {
        if(uid == null) model.addAttribute("boardTypeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("boardTypeUid", uid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/admin/board/report/"+type);
        return "admin/board-report/" + type + "/index";
    }

}
