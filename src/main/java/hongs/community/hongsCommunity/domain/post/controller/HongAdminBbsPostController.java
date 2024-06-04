package hongs.community.hongsCommunity.domain.post.controller;

import hongs.community.hongsCommunity.domain.bbs.HongBbsTypeService;
import hongs.community.hongsCommunity.domain.post.HongBbsPostService;
import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostListDto;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostListVo;
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
* @fileName HongAdminBbsPostController
* @author dahyeon
* @version 1.0.0
* @date 2024-05-20
* @summary      type : notice, faq, qnq
*               typeUid : bbs type uid
*               postUid : post uid
**/
@RequiredArgsConstructor
@RequestMapping("admin/bbs/post")
@Controller
public class HongAdminBbsPostController {

    private final HongBbsTypeService bbsTypeService;
    private final HongBbsPostService bbsPostService;

    @GetMapping("/{type}")
    public String index(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) {
            typeUid = bbsTypeService.latestBbsType(type);
            model.addAttribute("typeUid", typeUid);
        } else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("url", "/admin/bbs/post/"+type);

        if("faq".equals(type)) {
            List<HongBbsPostListVo> faqList = bbsPostService.list(new HongBbsPostListDto(typeUid, type)).stream().map(post -> {
                post.setPstCn(StringUtil.unescape(post.getPstCn()));
                return post;
            }).toList();
            model.addAttribute("faqList", faqList);
            model.addAttribute("typeView", bbsTypeService.view(typeUid));
        }

        return "admin/post/" + type + "/index";
    }

    @GetMapping("/{type}/form")
    public String form(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid, Model model) {
        if(typeUid == null) model.addAttribute("typeUid", bbsTypeService.latestBbsType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("typeView", bbsTypeService.view(typeUid));
        model.addAttribute("url", "/admin/bbs/post/"+type);
        return "admin/post/" + type + "/form";
    }

    @GetMapping("/{type}/edit/{postUid}")
    public String edit(@PathVariable(name = "type") String type, @RequestParam(name = "typeUid", required = false) Long typeUid
            , @PathVariable(name = "postUid") Long postUid, Model model) {

        if(typeUid == null) model.addAttribute("typeUid", bbsTypeService.latestBbsType(type));
        else model.addAttribute("typeUid", typeUid);
        model.addAttribute("type", type);
        model.addAttribute("typeView", bbsTypeService.view(typeUid));

        HongBbsPostViewVo postView = bbsPostService.view(postUid);
        postView.setPstCn(StringUtil.unescape(postView.getPstCn()));
        model.addAttribute("postView", postView);

        model.addAttribute("url", "/admin/bbs/post/"+type);
        return "admin/post/" + type + "/edit";
    }

}
