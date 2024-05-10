package hongs.community.hongsCommunity.domain.code;

import hongs.community.hongsCommunity.domain.code.dto.HongCodeChildMergeDto;
import hongs.community.hongsCommunity.domain.code.dto.HongCodeDeleteDto;
import hongs.community.hongsCommunity.domain.code.dto.HongCodeInsertDto;
import hongs.community.hongsCommunity.domain.code.dto.HongCodeUpdateDto;
import hongs.community.hongsCommunity.domain.code.vo.HongChildCodeListVo;
import hongs.community.hongsCommunity.domain.code.vo.HongCodeViewVo;
import hongs.community.hongsCommunity.domain.code.vo.HongUpperCodeListVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongCodeMapper {

    List<HongUpperCodeListVo> upperList();

    Integer checkIfDuplicate(String codeVal);

    Integer insert(HongCodeInsertDto dto);

    HongCodeViewVo upperView(Long hongCodeUid);

    Integer delete(HongCodeDeleteDto dto);

    Integer update(HongCodeUpdateDto dto);

    Integer insertEach(HongCodeChildMergeDto dto);

    Integer updateEach(HongCodeChildMergeDto dto);

    Integer deleteEach(HongCodeChildMergeDto dto);

    List<HongChildCodeListVo> childList(Long hongCodeUid);

    List<HongChildCodeListVo> childListByVal(String upperCodeVal);

}
