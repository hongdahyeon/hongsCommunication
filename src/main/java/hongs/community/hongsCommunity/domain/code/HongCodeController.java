package hongs.community.hongsCommunity.domain.code;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("super/code")
@Controller
public class HongCodeController {

    @GetMapping({"", "/"})
    public String index(){
        return "super/code/index";
    }
}
