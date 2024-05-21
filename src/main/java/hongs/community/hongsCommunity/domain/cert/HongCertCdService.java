package hongs.community.hongsCommunity.domain.cert;

import hongs.community.hongsCommunity.domain.cert.dto.HongCertCdInsertDto;
import hongs.community.hongsCommunity.domain.cert.dto.HongCertCheckDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

@Service
@RequiredArgsConstructor
public class HongCertCdService {

    private final HongCertCdMapper mapper;

    public Integer join(HongCertCdInsertDto dto){
        return mapper.join(dto);
    }

    public Boolean checkCertCd(HongCertCheckDto dto) {
        String checkedCertCd = mapper.checkCertCd(dto);
        if(!StringUtils.equals(dto.getCertCd(), checkedCertCd)) return false;
        else {
            mapper.changeUserLastLoginDate(dto.getUserId());
            mapper.changeCertCdUseYn(dto.getUserEmail());
            return true;
        }
    }
}