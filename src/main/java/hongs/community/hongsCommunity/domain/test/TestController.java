package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.domain.test.vo.TestVo;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("test")
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;

    private void setModalUrl(final Model model) {
        model.addAttribute("menuUrl", "/test");
    }

    @GetMapping({"/", ""})
    public String index(Model model){
        this.setModalUrl(model);
        return "test/index";
    }

    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable(name = "uid") Long uid, Model model) {
        this.setModalUrl(model);
        TestVo view = testService.view(uid);
        view.setName(StringUtil.unescape(view.getName()));
        model.addAttribute("fUid", view.getFileUid());
        model.addAttribute("view", view);
        return "test/edit";
    }

    @GetMapping("/view/{uid}")
    public String view(@PathVariable(name = "uid") Long uid, Model model) {
        this.setModalUrl(model);
        TestVo view = testService.view(uid);
        view.setName(StringUtil.unescape(view.getName()));
        model.addAttribute("fUid", view.getFileUid());
        model.addAttribute("view", view);
        return "test/view";
    }
}
