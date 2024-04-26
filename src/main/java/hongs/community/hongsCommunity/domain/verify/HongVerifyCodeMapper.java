package hongs.community.hongsCommunity.domain.verify;

import hongs.community.hongsCommunity.domain.verify.dto.HongVerifyCheckDto;
import hongs.community.hongsCommunity.domain.verify.dto.HongVerifyCodeInsertDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongVerifyCodeMapper {

    Integer join(HongVerifyCodeInsertDto dto);

    String checkVerify(HongVerifyCheckDto dto);

    void changeUserLastLoginDate(String userId);

    void changeVerifyCheck(String userEmail);
}