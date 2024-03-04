package hongs.community.hongsCommunity.global.hongs.file;

import hongs.community.hongsCommunity.global.hongs.file.dto.HongChangeStateFileDto;
import hongs.community.hongsCommunity.global.hongs.file.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.dto.HongSaveFileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongFileMapper {
    void insertTempFile(HongSaveFileDto dto);

    Long generateKey();

    void deleteFileReal(HongDeleteFileDto dto);

    void changeFileState(HongChangeStateFileDto dto);
}