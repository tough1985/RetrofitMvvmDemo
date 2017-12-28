package me.xiba.startlearnmvvm.difftype.fragment;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajguan.library.EasyRefreshLayout;

import me.xiba.startlearnmvvm.BR;
import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.databinding.FragmentDiffTypeBinding;
import me.xiba.startlearnmvvm.difftype.entity.TypeA;
import me.xiba.startlearnmvvm.difftype.entity.TypeB;
import me.xiba.startlearnmvvm.difftype.event.DiffTypeItemBEvent;
import me.xiba.startlearnmvvm.difftype.viewmodel.DiffTypeItemBVM;
import me.xiba.startlearnmvvm.difftype.viewmodel.DiffTypeVM;
import me.xiba.startlearnmvvm.difftype.factory.DiffTypeViewModelFactory;
import me.xiba.startlearnmvvm.movie.adapter.BindingRecyclerAdapter;

/**
 * @Author:liukun
 * @Date: 2017-12-27 11:36
 * @Description: 数据类型为TypeB的列表
 */
public class DiffTypeBFragment extends Fragment {

    private FragmentDiffTypeBinding mBinding;

    private DiffTypeVM mViewModel;

    private BindingRecyclerAdapter mAdapter;

    //数据为TypeB时的事件处理
    private DiffTypeItemBEvent mEvent;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //进行binding，并设置对应的ViewModel
        mBinding = FragmentDiffTypeBinding.inflate(inflater, container, false);
        mBinding.setMDiffTypeVM(getViewModel());

        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mEvent = new DiffTypeItemBEvent();

        //设定ViewModel，布局文件，事件处理和item布局所对应的ViewModel
        mAdapter = new BindingRecyclerAdapter<TypeA, DiffTypeItemBVM>(BR.viewModel,
                R.layout.item_diff_type,
                BR.event,
                mEvent,
                DiffTypeItemBVM.class);

        mBinding.diffTypeRV.setAdapter(mAdapter);

        mBinding.diffTypeRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.diffTypeRV.setHasFixedSize(true);

//        mBinding.movieTopListERL.setLoadMoreView(getLayoutInflater().inflate(R.layout.item_load_more, mBinding.movieTopListRV));
        mBinding.diffTypeERL.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                //上拉刷新，向ViewModel请求数据
                getViewModel().getData();
            }

            @Override
            public void onRefreshing() {

            }
        });

        //设置不可下拉刷新
        mBinding.diffTypeERL.setEnablePullToRefresh(false);

        getViewModel().getLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean showLoading) {
                if (!showLoading) {
                    //关闭底部的刷新控件
                    mBinding.diffTypeERL.closeLoadView();
                }
            }
        });

        //向ViewModel请求数据
        getViewModel().getData();
    }

    //获取ViewModel，通过工厂的方式获取不同数据类型的ViewModel
    public DiffTypeVM getViewModel(){
        if (mViewModel == null) {
            mViewModel = ViewModelProviders.of(this, new DiffTypeViewModelFactory(TypeB.class)).get(DiffTypeVM.class);

        }
        return mViewModel;
    }

}
