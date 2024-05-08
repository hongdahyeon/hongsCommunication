package hongs.community.hongsCommunity.domain.disable;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("admin/user")
@RequiredArgsConstructor
public class HongUserDisableController {

    @GetMapping({"", "/"})
    public String index() {
        return "admin/user/index";
    }
}