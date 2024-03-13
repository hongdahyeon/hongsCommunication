package hongs.community.hongsCommunity.domain.user;

import hongs.community.hongsCommunity.domain.user.dto.HongUserInsertDto;
import hongs.community.hongsCommunity.domain.user.vo.HongLoginUserVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongUserMapper {

    HongLoginUserVo findUser(String userId);

    Integer joinUser(HongUserInsertDto dto);
}
