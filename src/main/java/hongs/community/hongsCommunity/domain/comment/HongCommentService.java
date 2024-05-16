package hongs.community.hongsCommunity.domain.comment;

import hongs.community.hongsCommunity.domain.comment.dto.HongCommentListDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentInsertDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentUpdateDto;
import hongs.community.hongsCommunity.domain.comment.vo.HongCommentListVo;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongCommentService {

    private final HongCommentMapper commentMapper;

    public List<HongCommentListVo> list(HongCommentListDto dto){
        dto.setLoginUser(UserUtil.getLoginUserUid());
        return commentMapper.list(dto);
    }

    public Integer insert(HongCommentInsertDto dto) {
        return commentMapper.insert(dto);
    }

    public Integer delete(Long commentUid) {
        return commentMapper.delete(commentUid);
    }

    public Integer update(HongCommentUpdateDto dto) {
        return commentMapper.update(dto);
    }
}
