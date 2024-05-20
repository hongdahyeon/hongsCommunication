package hongs.community.hongsCommunity.domain.report.user;

import hongs.community.hongsCommunity.domain.board.HongBoardTypeService;
import hongs.community.hongsCommunity.domain.report.user.vo.HongBoardReportViewVo;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@RequestMapping("board/report")
@Controller
public class HongBoardReportController {

    private final HongBoardTypeService boardTypeService;
    private final HongBoardReportService boardReportService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) model.addAttribute("typeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/board/report/"+type);
        return "report/" + type + "/index";
    }

    @GetMapping("/{type}/view/{reportUid}")
    public String view(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid
            , @PathVariable(name = "reportUid") Long reportUid, Model model) {
        if(typeUid == null) model.addAttribute("typeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/board/report/"+type);

        HongBoardReportViewVo reportView = boardReportService.view(reportUid);
        reportView.setContent(StringUtil.unescape(reportView.getContent()));
        model.addAttribute("reportView", reportView);

        return "report/" + type + "/view";
    }
}
