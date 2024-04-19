package hongs.community.hongsCommunity.domain.calendar;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("calendar")
public class HongCalendarController {

    @GetMapping({"/", ""})
    public String test2(){
        return "calendar/index";
    }
}