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
import org.springframework.web.bind.annotation.RequestParam;

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
    public String index(@RequestParam(name = "search", required = false) String search, Model model) {
        this.setModalUrl(model);
        if (search == null) search = "all";
        model.addAttribute("search", search);
        model.addAttribute("teams", teamService.list(search));
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
        model.addAttribute("type", "TEAM_VIEW_COMM");
        return "team/view";
    }

    @GetMapping("/edit/{uid}")
    public String edit(@PathVariable(name = "uid") Long uid, Model model) {
        this.setModalUrl(model);
        HongTeamViewVo teamEdit = teamService.view(uid);
        teamEdit.setTeamIntro(StringUtil.unescape(teamEdit.getTeamIntro()));
        model.addAttribute("teamCategory", codeService.childListByVal("TEAM_CATEGORY"));
        model.addAttribute("teamEdit", teamEdit);
        return "team/edit";
    }

    @GetMapping("/user/list/{uid}")
    public String userList(@PathVariable(name = "uid") Long uid, Model model) {
        this.setModalUrl(model);
        return "team/user/index";
    }
}
