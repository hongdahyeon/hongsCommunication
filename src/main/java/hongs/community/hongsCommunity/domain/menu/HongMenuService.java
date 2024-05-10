package hongs.community.hongsCommunity.domain.menu;

import hongs.community.hongsCommunity.domain.menu.dto.HongMenuDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuInsertUpdateDto;
import hongs.community.hongsCommunity.domain.menu.dto.HongMenuSuperDto;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuSuperVo;
import hongs.community.hongsCommunity.domain.menu.vo.HongMenuVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HongMenuService {

    private final HongMenuMapper mapper;

    public List<HongMenuVo> list(String menuRole) {

        /* 1. 부모 리스트 */
        List<HongMenuVo> parentList = mapper.parentList(new HongMenuDto(menuRole, "Y", "N"));
        return parentList.stream().map(hongMenuVo -> {

            /* 2. 자식 리스트 */
            List<HongMenuVo> childList = mapper.childList(new HongMenuDto(hongMenuVo.getMenuUid(), "Y", "N"));

            /* 3. 부모+자식 url 리스트 */
            List<String> urlList = new ArrayList<>();
            if(!childList.isEmpty()) urlList = childList.stream().map(childs -> childs.getMenuUrl()).toList();
            if(hongMenuVo.getMenuUrl() != null && hongMenuVo.getMenuUrl().length() > 0) urlList.add(hongMenuVo.getMenuUrl());

            hongMenuVo.setChildren(childList);
            hongMenuVo.setUrlList(urlList);
            return hongMenuVo;
        }).toList();
    }

    public List<HongMenuSuperVo> superList(HongMenuSuperDto dto) {
        return mapper.superList(dto);
    }

    @Transactional(readOnly = false)
    public Integer join(HongMenuInsertUpdateDto dto) {
        return mapper.join(dto);
    }

    @Transactional(readOnly = false)
    public Integer update(HongMenuInsertUpdateDto dto){
        return mapper.update(dto);
    }

    @Transactional(readOnly = false)
    public Integer delete(Long menuUid) {
        return mapper.delete(menuUid);
    }
}
