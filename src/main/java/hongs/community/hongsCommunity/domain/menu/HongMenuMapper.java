package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuInsertUpdateDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuSuperDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuSuperVo;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongMenuMapper {

    List<HongMenuVo> parentList(HongMenuDto hongMenuDto);

    List<HongMenuVo> childList(HongMenuDto hongMenuDto);

    List<HongMenuSuperVo> superList(HongMenuSuperDto dto);

    Integer join(HongMenuInsertUpdateDto dto);

    Integer update(HongMenuInsertUpdateDto dto);

    Integer delete(Long menuUid);

    String getLandingPage(String userRole);
}

