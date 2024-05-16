package hongs.community.hongsCommunity.domain.board;

import hongs.community.hongsCommunity.domain.board.dto.HongBoardTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeListVo;
import hongs.community.hongsCommunity.domain.board.vo.HongBoardTypeViewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongBoardTypeService {

    private final HongBoardTypeMapper boardTypeMapper;

    public List<HongBoardTypeListVo> list() {
        return boardTypeMapper.list();
    }

    public Integer insert(HongBoardTypeInsertUpdateDto dto) {
        return boardTypeMapper.insert(dto);
    }

    public HongBoardTypeViewVo view(Long hongBoardTypeUid) {
        return boardTypeMapper.view(hongBoardTypeUid);
    }

    public Integer update(HongBoardTypeInsertUpdateDto dto){
        return boardTypeMapper.update(dto);
    }

    public Integer delete(Long hongBoardTypeUid){
        return boardTypeMapper.delete(hongBoardTypeUid);
    }

    public Long latestBoardType(String hongBoardType) {
        return boardTypeMapper.latestBoardType(hongBoardType);
    }
}
