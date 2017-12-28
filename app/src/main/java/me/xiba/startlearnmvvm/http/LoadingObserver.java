package me.xiba.startlearnmvvm.http;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;
import me.xiba.startlearnmvvm.util.ResUtil;
import me.xiba.startlearnmvvm.util.SysUtils;

/**
 * Created by liukun on 2017/12/21.
 */

public class LoadingObserver<T> implements Observer<T> {

    private Disposable mDisposable;

    private ObserverOnNextListener<T> mOnNextListener;
    private ObserverOnErrorListener mOnErrorListener;

    private ObserverOnLoadingListener mOnLoadingListener;

    private SingleLiveEvent<Boolean> mShowLoading;

    public LoadingObserver(ObserverOnNextListener<T> mOnNextListener, ObserverOnErrorListener mOnErrorListener, SingleLiveEvent<Boolean> mShowLoading) {
        this.mOnNextListener = mOnNextListener;
        this.mOnErrorListener = mOnErrorListener;
        this.mShowLoading = mShowLoading;
    }

    /**
     * 设置loading监听，默认调用Activity的LoadingDialog
     *
     * @param onLoadingListener
     */
    public void setOnLoadingListener(ObserverOnLoadingListener onLoadingListener) {
        mOnLoadingListener = onLoadingListener;
    }

    @Override
    public void onSubscribe(Disposable d) {
        mDisposable = d;


        if (!SysUtils.isNetWorkConnected()) {
            String errorMsg = ResUtil.getString(R.string.net_error);

//            ApiException apiException = new ApiException(-10001, errorMsg, null);
            //如果设置了监听，通过监听处理
            if (mOnErrorListener != null) {
                mOnErrorListener.observerOnError(errorMsg);
            } else {
//                mMvpView.showError(ResUtil.getString(R.string.net_error));
            }

            mDisposable.dispose();
        } else {
            //显示loading
            if (mOnLoadingListener != null) {
                mOnLoadingListener.onShowLoading();
            } else {
                mShowLoading.setValue(true);
            }
        }

    }

    @Override
    public void onNext(T value) {

        if (mOnLoadingListener != null) {
            mOnLoadingListener.onDismissLoading();
        } else {
            mShowLoading.setValue(false);
        }

        if (mOnNextListener != null) {
            mOnNextListener.observerOnNext(value);
        }
    }

    @Override
    public void onError(Throwable e) {
        if (mOnLoadingListener != null) {
            mOnLoadingListener.onDismissLoading();
        } else {
            mShowLoading.setValue(false);
        }

        //如果设置了监听，通过监听处理
        if (mOnErrorListener != null) {
            mOnErrorListener.observerOnError(e.getMessage());
        }
    }

    @Override
    public void onComplete() {

    }

    /**
     * 取消请求
     */
    public void cancelRequest() {
        if (mOnLoadingListener != null) {
            mOnLoadingListener.onDismissLoading();
        } else {
            mShowLoading.setValue(false);
        }

        if (mDisposable != null && !mDisposable.isDisposed()) {
            mDisposable.dispose();
        }
    }


    public interface ObserverOnNextListener<T> {
        void observerOnNext(T value);
    }

    public interface ObserverOnErrorListener {
        void observerOnError(String errorMsg);
    }

    public interface ObserverOnLoadingListener {
        void onShowLoading();

        void onDismissLoading();
    }
}
