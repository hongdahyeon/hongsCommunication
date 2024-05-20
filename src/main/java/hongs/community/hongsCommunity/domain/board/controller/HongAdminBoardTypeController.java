package hongs.community.hongsCommunity.domain.board.controller;

import hongs.community.hongsCommunity.domain.code.HongCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("admin/board/type")
@Controller
public class HongAdminBoardTypeController {

    private final HongCodeService codeService;

    private void setModalUrl(final Model model) {
        model.addAttribute("url", "/admin/board/type");
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        this.setModalUrl(model);
        model.addAttribute("boardType", codeService.childListByVal("BOARD_TYPE"));
        return "admin/board-type/index";
    }
}
