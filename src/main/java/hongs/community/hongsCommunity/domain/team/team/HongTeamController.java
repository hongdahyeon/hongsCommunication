package hongs.community.hongsCommunity.domain.team.team;


import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("team")
public class HongTeamController {

    private final HongTeamService teamService;

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/team");
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        this.setModalUrl(model);
        model.addAttribute("teams", teamService.list());
        return "team/index";
    }

    @GetMapping("/form")
    public String form(Model model) {
        this.setModalUrl(model);
        return "team/form";
    }
}
