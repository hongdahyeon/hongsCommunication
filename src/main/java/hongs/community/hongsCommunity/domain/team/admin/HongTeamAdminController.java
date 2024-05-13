package hongs.community.hongsCommunity.domain.team.admin;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @fileName HongTeamAdminController
* @author dahyeon
* @version 1.0.0
* @date 2024-05-13
* @summary  AMIN 권한에서 팀 정보 관리
**/

@Controller
@RequiredArgsConstructor
@RequestMapping("admin/team")
public class HongTeamAdminController {

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/admin/team");
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        this.setModalUrl(model);
        return "admin/team/index";
    }
}
