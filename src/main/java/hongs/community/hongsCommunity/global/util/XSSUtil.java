package hongs.community.hongsCommunity.global.util;

import org.owasp.encoder.Encode;

public class XSSUtil {

    public static String charEscape(String value){
        return value == null ? null :  Encode.forHtmlContent(value);
    }
}