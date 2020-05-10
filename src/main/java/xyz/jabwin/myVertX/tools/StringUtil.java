package xyz.jabwin.myVertX.tools;


import org.springframework.lang.Nullable;

import java.util.stream.Stream;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class StringUtil
{
    /**
     * 是否全非 Blank
     *
     * @param css CharSequence
     * @return boolean
     */
    public static boolean isNoneBlank(final CharSequence... css) {
        if (ObjectUtil.isEmpty(css)) {
            return false;
        }
        return Stream.of(css).allMatch(StringUtil::isNotBlank);
    }
    public static boolean isNotBlank(final CharSequence cs) {
        return StringUtil.hasText(cs);
    }
    public static boolean hasText(@Nullable CharSequence str) {
        return str != null && str.length() > 0 && containsText(str);
    }
    private static boolean containsText(CharSequence str) {
        int strLen = str.length();

        for(int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return true;
            }
        }

        return false;
    }
}
