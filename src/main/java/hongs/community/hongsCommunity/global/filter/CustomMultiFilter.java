package hongs.community.hongsCommunity.global.filter;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.support.MultipartFilter;

@WebFilter("/*")
@Component
@Order(1)
public class CustomMultiFilter extends MultipartFilter {
}
