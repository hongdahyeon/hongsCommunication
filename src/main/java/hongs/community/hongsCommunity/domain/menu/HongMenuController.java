package hongs.community.hongsCommunity.domain.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @fileName HongMenuController
* @author dahyeon
* @version 1.0.0
* @date 2024-04-24
* @summary  "/super/menu/**" : super 권한의 사용자만 접근 가능
**/

@Controller
@RequiredArgsConstructor
@RequestMapping("super/menu")
public class HongMenuController {

    private void setModalUrl(final Model model) {
        model.addAttribute("menuUrl", "/super/menu");
    }

    @GetMapping({"/", ""})
    public String index(Model model) {
        this.setModalUrl(model);
        return "super/menu/index";
    }
}
