package hongs.community.hongsCommunity.domain.user.after;

import hongs.community.hongsCommunity.global.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public String index(Model model, @AuthenticationPrincipal PrincipalDetails principalDetails){
        Long userUid = principalDetails.getUser().getUserUid();
        model.addAttribute("user", userService.userInfo(userUid));
        return "profile/index";
    }
}
