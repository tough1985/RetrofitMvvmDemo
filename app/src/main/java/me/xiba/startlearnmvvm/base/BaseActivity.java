package me.xiba.startlearnmvvm.base;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import me.xiba.startlearnmvvm.LoadingDialog;

/**
 * Created by liukun on 2017/12/21.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private LoadingDialog mLoadingDialog;

    private BaseViewModel mViewModel;

    protected abstract <T extends BaseViewModel> T getViewModel();

    public void init(){

        mViewModel = getViewModel();

        if (mViewModel != null) {
            //订阅是否显示Loading对话框事件
            mViewModel.getShowLoading().observe(this, new Observer<Boolean>() {
                @Override
                public void onChanged(@Nullable Boolean loading) {
                    if (loading) {
                        showLoading();
                    } else {
                        dismissLoading();
                    }
                }
            });
        }
    }

    /**
     * 显示对话框
     */
    public void showLoading(){
        if (mLoadingDialog == null) {
            initDialog();
        }

        if (!mLoadingDialog.isShowing()){
            mLoadingDialog.show();
        }
    }

    /**
     * 隐藏对话框
     */
    public void dismissLoading(){

        if (mLoadingDialog == null) {
            initDialog();
        }

        if (mLoadingDialog.isShowing()){
            mLoadingDialog.dismiss();
        }

    }

    private void initDialog(){

        mLoadingDialog = new LoadingDialog(this);
        mLoadingDialog.setCancelable(true);

    }

    @Override
    protected void onDestroy() {
        if (mLoadingDialog != null) {
            if (mLoadingDialog.isShowing()) {
                mLoadingDialog.dismiss();
            }
            mLoadingDialog = null;
        }
        if (mViewModel != null) {
            mViewModel = null;
        }
        super.onDestroy();
    }
}
