package hongs.community.hongsCommunity.domain.postAprs;

import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPostAprsDeleteDto;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsInsertDto;
import hongs.community.hongsCommunity.domain.postAprs.dto.HongBbsPstAprsListDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HongBbsPstAprsService {

    private final HongBbsPstAprsMapper bbsPstAprsMapper;

    public Boolean bbsPstAprsTF(HongBbsPstAprsListDto dto) {
        Integer aprsCount = bbsPstAprsMapper.bbsPstAprsCount(dto);
        return (aprsCount == 1);
    }

    public Integer bbsPstAprsCount(HongBbsPstAprsListDto dto) {
        return bbsPstAprsMapper.bbsPstAprsCount(dto);
    }

    public Integer insert(HongBbsPstAprsInsertDto dto){
        Integer insert = bbsPstAprsMapper.insert(dto);
        Integer aprsCount = this.bbsPstAprsCount(new HongBbsPstAprsListDto(dto.getBbsPstUid(), dto.getAprsCd(), "false"));
        return (insert == 1) ? aprsCount : (aprsCount - 1) ;
    }

    public Integer delete(HongBbsPostAprsDeleteDto dto) {
        Integer delete = bbsPstAprsMapper.delete(dto);
        Integer emoCount = this.bbsPstAprsCount(new HongBbsPstAprsListDto(dto.getBbsPstUid(), dto.getAprsCd(), "false"));
        return (delete == 1) ? emoCount : (emoCount + 1) ;
    }
}
