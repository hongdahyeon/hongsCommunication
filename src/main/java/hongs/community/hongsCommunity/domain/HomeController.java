package hongs.community.hongsCommunity.domain;

import hongs.community.hongsCommunity.domain.test.vo.TestVo;
import hongs.community.hongsCommunity.domain.test.TestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
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
    public String index(){
        List<TestVo> testList = testService.getTestList();
        log.info("testList : {} ", testList);
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "/login";
    }
}