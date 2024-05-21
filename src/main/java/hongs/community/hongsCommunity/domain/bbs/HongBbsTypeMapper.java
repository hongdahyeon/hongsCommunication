package hongs.community.hongsCommunity.domain.bbs;

import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsYnUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeListVo;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongBbsTypeMapper {

    List<HongBbsTypeListVo> list();

    Integer insert(HongBbsTypeInsertUpdateDto dto);

    HongBbsTypeViewVo view(Long hongBbsTypeUid);

    Integer update(HongBbsTypeInsertUpdateDto dto);

    Integer delete(Long hongBbsTypeUid);

    Long latestBbsType(String bbsTypeCd);

    Integer ynUpdate(HongBbsYnUpdateDto dto);
}
