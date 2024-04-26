package hongs.community.hongsCommunity.domain.verify;

import hongs.community.hongsCommunity.domain.verify.dto.HongVerifyCheckDto;
import hongs.community.hongsCommunity.domain.verify.dto.HongVerifyCodeInsertDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class HongVerifyCodeService {

    private final HongVerifyCodeMapper mapper;

    public Integer join(HongVerifyCodeInsertDto dto){
        return mapper.join(dto);
    }

    public Boolean checkVerify(HongVerifyCheckDto dto) {
        String checkedVerify = mapper.checkVerify(dto);
        if(!StringUtils.equals(dto.getVerifyCode(), checkedVerify)) return false;
        else {
            mapper.changeUserLastLoginDate(dto.getUserId());
            mapper.changeVerifyCheck(dto.getUserEmail());
            return true;
        }
    }
}