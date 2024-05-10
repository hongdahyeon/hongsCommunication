package hongs.community.hongsCommunity.domain.disable;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user")
@RequiredArgsConstructor
public class HongUserDisableController {

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/admin/user");
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        this.setModalUrl(model);
        return "admin/user/index";
    }
}