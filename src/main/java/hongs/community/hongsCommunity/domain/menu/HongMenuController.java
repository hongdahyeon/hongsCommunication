package hongs.community.hongsCommunity.domain.menu;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("super/menu")
public class HongMenuController {

    @GetMapping({"/", ""})
    public String index() {
        return "menu/index";
    }
}
