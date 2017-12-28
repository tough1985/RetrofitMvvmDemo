package me.xiba.startlearnmvvm.util;

import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;

import me.xiba.startlearnmvvm.MyApplication;

/**
 * Created by liukun on 2017/10/9.
 * 获取资源的工具类
 */
public class ResUtil {

    public static String getString(int resId) {
        return MyApplication.getInstance().getString(resId);
    }

    public static int getColor(int resId) {
        return ContextCompat.getColor(MyApplication.getInstance(), resId);
    }

    public static Drawable getDrawable(int resId) {
        return ContextCompat.getDrawable(MyApplication.getInstance(), resId);
    }

    public static int getDimens(int resId) {
        return MyApplication.getInstance().getResources().getDimensionPixelSize(resId);
    }

    /**
     * 获取字体大小相同不同颜色的2个字符组成的字符串
     */
    public static Spannable getColorString(String font, String after, int fontColor, int afterColor){
        String connectStr = new StringBuilder().append(font).append(after).toString();
        Spannable span = new SpannableString(connectStr);
        int indexOfFont = connectStr.indexOf(font);
        int indexOfAfter = connectStr.indexOf(after);
        span.setSpan(new ForegroundColorSpan(fontColor), indexOfFont, indexOfFont + font.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        span.setSpan(new ForegroundColorSpan(afterColor), indexOfAfter, indexOfAfter + after.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return span;
    }

}
