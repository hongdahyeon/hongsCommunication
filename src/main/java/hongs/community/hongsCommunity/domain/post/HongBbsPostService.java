package hongs.community.hongsCommunity.domain.post;

import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostInsertUpdateDto;
import hongs.community.hongsCommunity.domain.post.dto.HongBbsPostListDto;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostListVo;
import hongs.community.hongsCommunity.domain.post.vo.HongBbsPostViewVo;
import hongs.community.hongsCommunity.global.hongs.file.common.HongCommonFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HongBbsPostService {

    private final HongBbsPostMapper bbsPostMapper;
    private final HongCommonFileService hongCommonFileService;

    public List<HongBbsPostListVo> list(HongBbsPostListDto dto){
        return bbsPostMapper.list(dto);
    }

    public Integer insert(HongBbsPostInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return bbsPostMapper.insert(dto);
    }

    public HongBbsPostViewVo view(Long hongBbsPstUid) {
        return bbsPostMapper.view(hongBbsPstUid);
    }

    public Integer update(HongBbsPostInsertUpdateDto dto) {
        Long fUid = hongCommonFileService.saveAndDelFiles(dto.getFileUid(), dto.getAddFile(), dto.getDelFile());
        dto.setFileUid(fUid);
        return bbsPostMapper.update(dto);
    }

    public Integer delete(Long hongBbsPstUid){
        return bbsPostMapper.delete(hongBbsPstUid);
    }
}
