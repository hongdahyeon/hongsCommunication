package hongs.community.hongsCommunity.domain.code;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("super/code")
@Controller
public class HongCodeController {

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/super/code");
    }

    @GetMapping({"", "/"})
    public String index(Model model){
        this.setModalUrl(model);
        return "super/code/index";
    }
}
