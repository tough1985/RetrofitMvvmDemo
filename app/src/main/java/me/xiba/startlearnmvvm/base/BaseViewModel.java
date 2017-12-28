package me.xiba.startlearnmvvm.base;

import android.arch.lifecycle.ViewModel;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;


/**
 * Created by liukun on 2017/12/21.
 */

public class BaseViewModel extends ViewModel {
    //LiveData 用来控制是否显示Loading对话框
    protected final SingleLiveEvent<Boolean> mShowLoading = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getShowLoading() {
        return mShowLoading;
    }
}
