package me.xiba.startlearnmvvm.util;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by liukun on 2017/12/21.
 */

public class ImageUtil {

    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String url){

        if (view != null && url != null) {
            Glide.with(view.getContext()).load(url).into(view);
        }
    }
}
