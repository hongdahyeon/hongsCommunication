package hongs.community.hongsCommunity.domain.cmnt;

import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntListDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntInsertDto;
import hongs.community.hongsCommunity.domain.cmnt.dto.HongCmntUpdateDto;
import hongs.community.hongsCommunity.domain.cmnt.vo.HongCmntListVo;
import hongs.community.hongsCommunity.global.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongCmntService {

    private final HongCmntMapper cmntMapper;

    public List<HongCmntListVo> list(HongCmntListDto dto){
        dto.setLoginUser(UserUtil.getLoginUserUid());
        return cmntMapper.list(dto);
    }

    public Integer insert(HongCmntInsertDto dto) {
        return cmntMapper.insert(dto);
    }

    public Integer delete(Long commentUid) {
        return cmntMapper.delete(commentUid);
    }

    public Integer update(HongCmntUpdateDto dto) {
        return cmntMapper.update(dto);
    }
}
