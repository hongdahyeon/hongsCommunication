package hongs.community.hongsCommunity.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${hong.ckEdtior.ckImage.pattern}")
    private String ckImgPattern;

    @Value("${hong.ckEdtior.ckImage.root}")
    private String ckImagePath;

    @Value("${hong.summernote.summernoteImage.pattern}")
    private String summerNotePattern;

    @Value("${hong.summernote.summernoteImage.root}")
    private String summerNoteImagePath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(ckImgPattern, summerNotePattern)
                .addResourceLocations("file:///" + ckImagePath, "file:///" + summerNoteImagePath);
    }
}
