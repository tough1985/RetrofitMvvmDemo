package me.xiba.startlearnmvvm.base;

/**
 * Created by liukun on 2017/12/26.
 */

public abstract class BaseAdapterViewModel<T> extends BaseViewModel {

    public abstract void setData(T t);
}
