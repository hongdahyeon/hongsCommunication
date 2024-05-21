package hongs.community.hongsCommunity.domain.bbs;

import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsTypeInsertUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.dto.HongBbsYnUpdateDto;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeListVo;
import hongs.community.hongsCommunity.domain.bbs.vo.HongBbsTypeViewVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongBbsTypeService {

    private final HongBbsTypeMapper bbsTypeMapper;

    public List<HongBbsTypeListVo> list() {
        return bbsTypeMapper.list();
    }

    public Integer insert(HongBbsTypeInsertUpdateDto dto) {
        return bbsTypeMapper.insert(dto);
    }

    public HongBbsTypeViewVo view(Long hongBbsTypeUid) {
        return bbsTypeMapper.view(hongBbsTypeUid);
    }

    public Integer update(HongBbsTypeInsertUpdateDto dto){
        return bbsTypeMapper.update(dto);
    }

    public Integer delete(Long hongBbsTypeUid){
        return bbsTypeMapper.delete(hongBbsTypeUid);
    }

    public Long latestBbsType(String bbsTypeCd) {
        return bbsTypeMapper.latestBbsType(bbsTypeCd);
    }

    public Integer ynUpdate(HongBbsYnUpdateDto dto) {
        return bbsTypeMapper.ynUpdate(dto);
    }
}
