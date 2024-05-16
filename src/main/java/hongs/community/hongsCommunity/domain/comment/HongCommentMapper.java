package hongs.community.hongsCommunity.domain.comment;

import hongs.community.hongsCommunity.domain.comment.dto.HongCommentListDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentInsertDto;
import hongs.community.hongsCommunity.domain.comment.dto.HongCommentUpdateDto;
import hongs.community.hongsCommunity.domain.comment.vo.HongCommentListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCommentMapper {

    List<HongCommentListVo> list(HongCommentListDto dto);

    Integer insert(HongCommentInsertDto dto);

    Integer delete(Long commentUid);

    Integer update(HongCommentUpdateDto dto);
}
