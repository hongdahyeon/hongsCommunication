package hongs.community.hongsCommunity.domain.report.admin;

import hongs.community.hongsCommunity.domain.board.HongBoardTypeService;
import hongs.community.hongsCommunity.domain.report.admin.vo.HongAdminBoardReportViewVo;
import hongs.community.hongsCommunity.global.util.StringUtil;
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
public class HongAdminBoardReportController {

    private final HongBoardTypeService boardTypeService;
    private final HongAdminBoardReportService boardReportService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) model.addAttribute("typeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/admin/board/report/"+type);
        return "admin/board-report/" + type + "/index";
    }

    @GetMapping("/{type}/form")
    public String form(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) model.addAttribute("typeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("typeView", boardTypeService.view(typeUid));
        model.addAttribute("url", "/admin/board/report/"+type);
        return "admin/board-report/" + type + "/form";
    }

    @GetMapping("/{type}/edit/{reportUid}")
    public String edit(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid
            , @PathVariable(name = "reportUid") Long reportUid, Model model) {

        if(typeUid == null) model.addAttribute("typeUid", boardTypeService.latestBoardType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("typeView", boardTypeService.view(typeUid));

        HongAdminBoardReportViewVo reportView = boardReportService.view(reportUid);
        reportView.setContent(StringUtil.unescape(reportView.getContent()));

        model.addAttribute("reportView", reportView);
        model.addAttribute("url", "/admin/board/report/"+type);
        return "admin/board-report/" + type + "/edit";
    }

}
