package hongs.community.hongsCommunity.domain.bbs.controller;

import hongs.community.hongsCommunity.domain.code.HongCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("admin/bbs/type")
@Controller
public class HongAdminBbsTypeController {

    private final HongCodeService codeService;

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/admin/bbs/type");
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        this.setModalUrl(model);
        model.addAttribute("bbsType", codeService.childListByVal("BBS_TYPE"));
        return "admin/bbs/index";
    }
}
