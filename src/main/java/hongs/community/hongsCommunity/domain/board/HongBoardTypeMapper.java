package hongs.community.hongsCommunity.domain.board;

import hongs.community.hongsCommunity.domain.board.dto.HongBoardTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.board.dto.HongBoardYnUpdateDto;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeListVo;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeViewVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface HongBoardTypeMapper {

    List<HongBoardTypeListVo> list();

    Integer insert(HongBoardTypeInsertUpdateDto dto);

    HongBoardTypeViewVo view(Long hongBoardTypeUid);

    Integer update(HongBoardTypeInsertUpdateDto dto);

    Integer delete(Long hongBoardTypeUid);

    Long latestBoardType(String hongBoardType);

    Integer ynUpdate(HongBoardYnUpdateDto dto);
}
