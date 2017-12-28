package me.xiba.startlearnmvvm.movie.adapter;

import android.arch.lifecycle.ViewModel;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import me.xiba.startlearnmvvm.base.BaseAdapterViewModel;

/**
 * Created by liukun on 2017/12/26.
 */

public class AdapterItemHolder<VM extends BaseAdapterViewModel> extends RecyclerView.ViewHolder {

    private ViewDataBinding mBinding;

    private VM mViewModel;

    public AdapterItemHolder(View itemView) {
        super(itemView);
    }

    public ViewDataBinding getBinding() {
        return mBinding;
    }

    public void setBinding(ViewDataBinding binding) {
        this.mBinding = binding;
    }

    public VM getViewModel() {
        return mViewModel;
    }

    public void setViewModel(VM viewModel) {
        this.mViewModel = viewModel;
    }
}
