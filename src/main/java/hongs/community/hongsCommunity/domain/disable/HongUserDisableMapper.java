package hongs.community.hongsCommunity.domain.disable;

import hongs.community.hongsCommunity.domain.disable.dto.HongUserDisableDto;
import hongs.community.hongsCommunity.domain.disable.dto.HongUserEnableDto;
import hongs.community.hongsCommunity.domain.disable.vo.HongUserDisableViewVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongUserDisableMapper {

    HongUserDisableViewVo view(Long userUid);

    Integer toEnable(HongUserEnableDto dto);

    Integer toDisable(HongUserDisableDto dto);
}
