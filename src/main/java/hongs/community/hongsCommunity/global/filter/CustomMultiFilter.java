package hongs.community.hongsCommunity.global.filter;

import jakarta.servlet.annotation.WebFilter;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@WebFilter("/*")
@Component
@Order(1)
public class CustomMultiFilter {
}
