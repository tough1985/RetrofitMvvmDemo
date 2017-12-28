package me.xiba.startlearnmvvm.util;

import java.util.List;

/**
 * Created by liukun on 2017/12/21.
 */

public class StringUtil {

    public static String joinItem(List<String> stringArray){
        if (stringArray == null) {
            return "";
        }

        StringBuilder sb = new StringBuilder();

        for (String s : stringArray) {
            sb.append(s + "/");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString().trim();
    }
}
