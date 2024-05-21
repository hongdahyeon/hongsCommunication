package hongs.community.hongsCommunity.domain.cmnt;

import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntListDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntInsertDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntUpdateDto;
import hongs.community.hongsCommunity.domain.cmnt.vo.HongCmntListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCmntMapper {

    List<HongCmntListVo> list(HongCmntListDto dto);

    Integer insert(HongCmntInsertDto dto);

    Integer delete(Long hongCmntUid);

    Integer update(HongCmntUpdateDto dto);
}
