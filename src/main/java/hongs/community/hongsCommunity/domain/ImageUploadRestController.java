package hongs.community.hongsCommunity.domain;


import hongs.community.hongsCommunity.global.util.FileUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("upload")
public class ImageUploadRestController {

    @Value("${hong.summernote.summernoteImage.root}")
    private String summernoteImgPath;

    @PostMapping(value = "/summernote/img", produces ="application/json")
    public Map<String, Object> uploadSummernoteImg(@RequestParam("file") MultipartFile multipartFile) {
        Map<String, Object> map = new HashMap<>();
        String originalFileName = multipartFile.getOriginalFilename();
        String extension = FileUtil.extension(originalFileName, true);
        UUID uuid = UUID.randomUUID();
        String savedFileName = uuid + extension;

        File targetFile = new File(String.format("%s%s%s", summernoteImgPath, File.separator, savedFileName));

        try{
            InputStream fileStream = multipartFile.getInputStream();
            FileUtils.copyInputStreamToFile(fileStream, targetFile);
            map.put("url", "/summernoteImage/" + savedFileName);
            map.put("responseCode", "success");
        } catch(IOException e) {
            map.put("responseCode", "error");
            e.printStackTrace();
        }
        return map;
    }

}
