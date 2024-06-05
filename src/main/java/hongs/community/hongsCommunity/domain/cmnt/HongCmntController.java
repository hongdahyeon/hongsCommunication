package hongs.community.hongsCommunity.domain.cmnt;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("cmnt")
public class HongCmntController {

    @GetMapping("/{type}/comment/{postUid}")
    public String commentPopup(@PathVariable(name = "type") String type, @PathVariable(name = "postUid") Long postUid, Model model) {
        model.addAttribute("commentParentType", "BBS_POST_COMM");
        model.addAttribute("commentParentUid", postUid);
        model.addAttribute("title", typeTitle(type));
        return "fragments/comments/commentPopup";
    }

    public String typeTitle(String type) {

        String title = "답변";

        if("faq".equals(type)) title = "FAQ 답변";
        else if("notice".equals(type)) title = "공지사항 답변";
        else if("qna".equals(type)) title = "1:1문의 답변";

        return title;
    }
}
