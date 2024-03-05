package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.domain.test.vo.TestVo;
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

    @GetMapping({"/", ""})
    public String index(){
        return "test/index";
    }

    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable(name = "uid") Long uid, Model model) {
        TestVo view = testService.view(uid);
        model.addAttribute("fUid", view.getFileUid());
        model.addAttribute("view", view);
        return "test/edit";
    }
}
