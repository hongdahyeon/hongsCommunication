package hongs.community.hongsCommunity.domain.code;


import hongs.community.hongsCommunity.domain.code.vo.HongChildCodeListVo;
import hongs.community.hongsCommunity.domain.code.vo.HongCodeViewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("super/code")
@Controller
public class HongCodeController {

    private final HongCodeService hongCodeService;

    private void setModalUrl(final Model model) {
        model.addAttribute("menuUrl", "/super/code");
    }

    @GetMapping({"", "/"})
    public String index(Model model){
        this.setModalUrl(model);
        return "super/code/index";
    }

    @GetMapping("/edit/{uid}")
    public String edit(Model model, @PathVariable(name = "uid", required = true) Long uid) {
        HongCodeViewVo view = hongCodeService.upperView(uid);
        List<HongChildCodeListVo> childList = hongCodeService.childList(uid);
        this.setModalUrl(model);
        model.addAttribute("uid", uid);
        model.addAttribute("upperUid", uid);
        model.addAttribute("upperView", view);
        model.addAttribute("childList", childList);
        return "super/code/edit";
    }
}
