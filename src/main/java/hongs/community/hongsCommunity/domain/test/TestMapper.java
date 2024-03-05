package hongs.community.hongsCommunity.domain.test;

import hongs.community.hongsCommunity.domain.test.dto.TestDto;
import hongs.community.hongsCommunity.domain.test.dto.TestFileDto;
import hongs.community.hongsCommunity.domain.test.vo.TestVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestVo> testList(TestDto testDto);

    void testSave(TestFileDto dto);
}
