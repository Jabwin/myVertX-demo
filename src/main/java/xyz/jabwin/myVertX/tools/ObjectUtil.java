package xyz.jabwin.myVertX.tools;

import org.springframework.lang.Nullable;

/*************************************************************
 * 类：
 * @author Jabwin
 *************************************************************
 */
public class ObjectUtil
{
    public static boolean isEmpty(@Nullable Object[] array) {
        return array == null || array.length == 0;
    }
}
