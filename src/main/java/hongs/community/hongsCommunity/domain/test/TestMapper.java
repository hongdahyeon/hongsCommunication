package hongs.community.hongsCommunity.domain.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TestMapper {
    List<TestVo> testList(TestDto testDto);

    void testSave(TestFileDto dto);
}
