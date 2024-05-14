package hongs.community.hongsCommunity.domain.team.team;


import hongs.community.hongsCommunity.domain.code.HongCodeService;
import hongs.community.hongsCommunity.domain.team.team.vo.HongTeamViewVo;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("team")
public class HongTeamController {

    private final HongTeamService teamService;
    private final HongCodeService codeService;

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
        model.addAttribute("teamCategory", codeService.childListByVal("TEAM_CATEGORY"));
        return "team/form";
    }

    @GetMapping("/view/{uid}")
    public String view(@PathVariable(name = "uid") Long uid, Model model) {
        this.setModalUrl(model);
        HongTeamViewVo teamView = teamService.view(uid);
        teamView.setTeamIntro(StringUtil.unescape(teamView.getTeamIntro()));
        model.addAttribute("teamView", teamView);
        return "team/view";
    }
}
