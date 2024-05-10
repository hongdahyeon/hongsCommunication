package hongs.community.hongsCommunity.domain.calendar;

import hongs.community.hongsCommunity.domain.code.HongCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
@RequiredArgsConstructor
public class HongCalendarController {

    private final HongCodeService hongCodeService;

    @GetMapping({"/", ""})
    public String index(Model model){
        model.addAttribute("colors", hongCodeService.childListByVal("CALENDAR_COLOR"));
        return "calendar/index";
    }
}