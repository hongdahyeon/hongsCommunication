package hongs.community.hongsCommunity.global.hongs.file.tus;

import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongChangeStateFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongDeleteFileDto;
import hongs.community.hongsCommunity.global.hongs.file.tus.dto.HongSaveFileDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface HongTusFileMapper {
    void insertTempFile(HongSaveFileDto dto);

    Long generateKey();

    void deleteFileReal(HongDeleteFileDto dto);

    void changeFileState(HongChangeStateFileDto dto);
}