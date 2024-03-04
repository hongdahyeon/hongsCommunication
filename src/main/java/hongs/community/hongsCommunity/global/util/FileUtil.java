package hongs.community.hongsCommunity.global.util;

public class FileUtil {

    public static String extension(String str, boolean withDot) {
        int lastIndex = (withDot) ? str.lastIndexOf(".") : str.lastIndexOf(".") + 1;
        return str.substring(lastIndex);
    }
}