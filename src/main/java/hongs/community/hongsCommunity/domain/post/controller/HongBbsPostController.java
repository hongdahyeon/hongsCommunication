package hongs.community.hongsCommunity.domain.post.controller;

import hongs.community.hongsCommunity.domain.bbs.HongBbsTypeService;
import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostListDto;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostListVo;
import hongs.community.hongsCommunity.domain.postAprs.HongBbsPstAprsService;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsListDto;
import hongs.community.hongsCommunity.domain.post.HongBbsPostService;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostViewVo;
import hongs.community.hongsCommunity.global.util.StringUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
* @fileName HongBbsPostController
* @author dahyeon
* @version 1.0.0
* @date 2024-05-20
* @summary  type : notice, faq, qnq
 *          typeUid : bbs type uid
 *          postUid : post uid
**/

@RequiredArgsConstructor
@RequestMapping("bbs/post")
@Controller
public class HongBbsPostController {

    private final HongBbsTypeService bbsTypeService;
    private final HongBbsPostService bbsPostService;
    private final HongBbsPstAprsService bbsPstAprsService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) typeUid = bbsTypeService.latestBbsType(type);
        model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/bbs/post/"+type);

        if("faq".equals(type)) {
            List<HongBbsPostListVo> faqList = bbsPostService.list(new HongBbsPostListDto(typeUid, type)).stream().map(post -> {
                post.setPstCn(StringUtil.unescape(post.getPstCn()));
                return post;
            }).toList();
            model.addAttribute("faqList", faqList);
            model.addAttribute("typeView", bbsTypeService.view(typeUid));
        }

        return "post/" + type + "/index";
    }

    @GetMapping("/{type}/view/{postUid}")
    public String view(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid
            , @PathVariable(name = "postUid") Long postUid, Model model) {

        if(typeUid == null) typeUid = bbsTypeService.latestBbsType(type);
        model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/bbs/post/"+type);

        HongBbsPostViewVo postView = bbsPostService.view(postUid);
        postView.setPstCn(StringUtil.unescape(postView.getPstCn()));
        model.addAttribute("postView", postView);
        model.addAttribute("typeView", bbsTypeService.view(typeUid));

        model.addAttribute("likes", bbsPstAprsService.bbsPstAprsTF(new HongBbsPstAprsListDto(postUid, "LIKE_EMO", "true")));
        model.addAttribute("likesCnt", bbsPstAprsService.bbsPstAprsCount(new HongBbsPstAprsListDto(postUid, "LIKE_EMO", "false")));
        model.addAttribute("dislikes", bbsPstAprsService.bbsPstAprsTF(new HongBbsPstAprsListDto(postUid, "DISLIKE_EMO", "true")));
        model.addAttribute("dislikesCnt", bbsPstAprsService.bbsPstAprsCount(new HongBbsPstAprsListDto(postUid, "DISLIKE_EMO", "false")));

        model.addAttribute("commentParentType", "BBS_POST_COMM");
        model.addAttribute("commentParentUid", postUid);

        return "post/" + type + "/view";
    }
}
