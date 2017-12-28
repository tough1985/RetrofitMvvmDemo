package me.xiba.startlearnmvvm.base;

import android.view.View;

/**
 * Created by liukun on 2017/12/26.
 */

public abstract class BaseEvent<T> {

    public abstract void onClick(View view, T t);
}
