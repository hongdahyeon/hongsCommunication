package hongs.community.hongsCommunity.domain.user.after.controller;

import hongs.community.hongsCommunity.domain.user.after.HongUserService;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user/info")
@RequiredArgsConstructor
public class HongUserController {

    private final HongUserService userService;

    @GetMapping({"", "/"})
    public String index(Model model){
        Long userUid = UserUtil.getLoginUser().getUserUid();
        model.addAttribute("user", userService.userInfo(userUid));
        return "profile/index";
    }
}
