package xyz.jabwin.myVertX.tools;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class TimeUtil
{
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    public static LocalDateTime convertTime(String str)
    {
        return LocalDateTime.parse(str, DATE_TIME_FORMATTER);
    }
}
