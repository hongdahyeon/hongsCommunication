package hongs.community.hongsCommunity.global.util;

import org.apache.commons.text.StringEscapeUtils;

import java.util.Random;

public class StringUtil {
    public static String unescape(String str) {
        return StringEscapeUtils.unescapeHtml4(str);
    }

    public static boolean startswith(String str, String prefix) {
        if(str == null || str.length() == 0) return false;
        else {
            return str.startsWith(prefix);
        }
    }

    public static boolean equal(String str1, String str2) {
        if(str1 == null || str2 == null) return false;
        else {
            return str1.equals(str2) && str2.equals(str1);
        }
    }

    public static String fixLength(String src, int limit) {
        if (src.length() > limit) {
            return src.substring(0, limit) + "...";
        }
        return src;
    }

    public static String random(int length){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            char randomChar = characters.charAt(index);
            password.append(randomChar);
        }

        return password.toString();
    }
}
