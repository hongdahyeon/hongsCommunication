package hongs.community.hongsCommunity.global.util;

import org.apache.commons.text.StringEscapeUtils;

public class StringUtil {
    public static String unescape(String str) {
        return StringEscapeUtils.unescapeHtml4(str);
    }
}
