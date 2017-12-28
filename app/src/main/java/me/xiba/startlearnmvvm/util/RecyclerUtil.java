package me.xiba.startlearnmvvm.util;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import me.xiba.startlearnmvvm.movie.adapter.BindingRecyclerAdapter;

/**
 * Created by liukun on 2017/12/25.
 * 用于给BindingAdapter传递数据
 */
public class RecyclerUtil {

    @SuppressWarnings("unchecked")
    @BindingAdapter("app:items")
    public static void setDatas(RecyclerView view, ArrayList data){
        BindingRecyclerAdapter adapter = (BindingRecyclerAdapter)view.getAdapter();
        if (adapter != null && data != null) {
            adapter.setData(data);
        }
    }
}
