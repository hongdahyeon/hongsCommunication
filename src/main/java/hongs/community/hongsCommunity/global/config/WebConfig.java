package hongs.community.hongsCommunity.global.config;

import hongs.community.hongsCommunity.global.interceptor.LoggingInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.VersionResourceResolver;

import java.util.concurrent.TimeUnit;

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

    @Bean
    public LoggingInterceptor loggingInterceptor(){
        return new LoggingInterceptor();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(ckImgPattern, summerNotePattern)
                .addResourceLocations("file:///" + ckImagePath, "file:///" + summerNoteImagePath);

        registry
                .addResourceHandler("/assets/**")
                .addResourceLocations("classpath:/static/assets/")
                .setCacheControl(CacheControl.maxAge(365, TimeUnit.DAYS))
                .resourceChain(true)
                .addResolver(new VersionResourceResolver().addContentVersionStrategy("/**"));
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(loggingInterceptor())
                .excludePathPatterns("/assets/**");
    }
}
