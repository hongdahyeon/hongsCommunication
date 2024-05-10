package hongs.community.hongsCommunity.domain;

import hongs.community.hongsCommunity.domain.test.vo.TestVo;
import hongs.community.hongsCommunity.domain.test.TestService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final TestService testService;

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("url", "/");
        List<TestVo> testList = testService.getTestList();
        log.info("testList : {} ", testList);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    @GetMapping("/searchId")
    public String searchId() {
        return "/searchId";
    }

    @GetMapping("/searchPwd")
    public String searchPwd() {
        return "/searchPwd";
    }

    @GetMapping("/join1")
    public String join1(HttpServletRequest req){
        String blocked = blockDirectAccess(req);
        return blocked != null ? blocked : "join/join1";
    }

    @GetMapping("/join2")
    public String join2(HttpServletRequest req){
        String blocked = blockDirectAccess(req);
        return blocked != null ? blocked : "join/join2";
    }

    public String blockDirectAccess(HttpServletRequest req) {
        String referer = req.getHeader("referer");
        if (referer == null) return "redirect:/";
        return null;
    }
}