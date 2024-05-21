package hongs.community.hongsCommunity.domain.post;

import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostInsertUpdateDto;
import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostListDto;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostListVo;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongBbsPostMapper {

    List<HongBbsPostListVo> list(HongBbsPostListDto dto);

    Integer insert(HongBbsPostInsertUpdateDto dto);

    HongBbsPostViewVo view(Long hongBbsPstUid);

    Integer update(HongBbsPostInsertUpdateDto dto);

    Integer delete(Long hongBbsPstUid);
}
