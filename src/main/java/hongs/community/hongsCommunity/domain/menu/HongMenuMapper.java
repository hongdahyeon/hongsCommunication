package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongMenuMapper {

    List<HongMenuVo> parentList(HongMenuDto hongMenuDto);

    List<HongMenuVo> childList(HongMenuDto hongMenuDto);
}

