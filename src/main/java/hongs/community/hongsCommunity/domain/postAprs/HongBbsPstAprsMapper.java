package hongs.community.hongsCommunity.domain.postAprs;

import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPostAprsDeleteDto;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsInsertDto;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsListDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongBbsPstAprsMapper {

    Integer bbsPstAprsCount(HongBbsPstAprsListDto dto);

    Integer insert(HongBbsPstAprsInsertDto dto);

    Integer delete(HongBbsPostAprsDeleteDto dto);
}