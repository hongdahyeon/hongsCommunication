package hongs.community.hongsCommunity.domain.report;

import hongs.community.hongsCommunity.domain.board.HongBoardTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("admin/board/report")
@Controller
public class HongBoardReportController {

    private final HongBoardTypeService boardTypeService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, Model model) {
        model.addAttribute("boardTypeUid", boardTypeService.latestBoardType(type.toUpperCase()));
        model.addAttribute("url", "/admin/board/report/"+type);
        return "admin/board-report/" + type + "/index";
    }

}
