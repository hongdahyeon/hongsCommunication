package hongs.community.hongsCommunity.domain.cert;

import hongs.community.hongsCommunity.domain.cert.dto.HongCertCdInsertDto;
import hongs.community.hongsCommunity.domain.cert.dto.HongCertCheckDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongCertCdMapper {

    Integer join(HongCertCdInsertDto dto);

    String checkCertCd(HongCertCheckDto dto);

    void changeUserLastLoginDate(String userId);

    void changeCertCdUseYn(String userEmail);
}