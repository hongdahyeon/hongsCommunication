package hongs.community.hongsCommunity.global.hongs.file.tus;

import jakarta.annotation.PreDestroy;
import me.desair.tus.server.TusFileUploadService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class TusFileConfig {

    @Value("${hong.tus.file.root}")
    private String tusStoragePath;

    @Value("${hong.tus.file.uri}")
    private String tusURI;

    @Value("${hong.tus.file.expiration}")
    private Long tusUploadExpirationPeriod;

    @PreDestroy
    public void exit() throws IOException {
        tus().cleanup();
    }

    @Bean
    public TusFileUploadService tus(){
        return new TusFileUploadService()
                .withStoragePath(tusStoragePath)
                .withDownloadFeature()
                .withUploadExpirationPeriod(tusUploadExpirationPeriod)
                .withUploadUri(tusURI);
    }
}
