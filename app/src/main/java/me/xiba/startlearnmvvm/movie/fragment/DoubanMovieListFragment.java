package me.xiba.startlearnmvvm.movie.fragment;

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
import com.ajguan.library.LoadModel;

import me.xiba.startlearnmvvm.BR;
import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.databinding.FragmentDoubanMovieListBinding;
import me.xiba.startlearnmvvm.movie.adapter.BindingRecyclerAdapter;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieItemEntity;
import me.xiba.startlearnmvvm.movie.event.MovieListItemEvent;
import me.xiba.startlearnmvvm.movie.viewmodel.DoubanMovieListVM;
import me.xiba.startlearnmvvm.movie.viewmodel.MovieListItemVM;

/**
 * @Author:liukun
 * @Date: 2017-12-27 18:04
 * @Description:
 */
public class DoubanMovieListFragment extends Fragment {

    public static final String KEY_PAGE_TYPE = "pageType";
    public int mPageType = 0;

    //正在热映
    public static final int PAGE_TYPE_IN_THEATERS = 0;
    //即将上映
    public static final int PAGE_TYPE_COMING_SOON = 1;
    //Top250
    public static final int PAGE_TYPE_TOP_250 = 2;

    private FragmentDoubanMovieListBinding mBinding;

    private DoubanMovieListVM mViewModel;

    private BindingRecyclerAdapter mAdapter;

    private MovieListItemEvent mEvent;

    public static DoubanMovieListFragment newInstance(int pageType){
        DoubanMovieListFragment fragment = new DoubanMovieListFragment();
        Bundle args = new Bundle();

        args.putInt(KEY_PAGE_TYPE, pageType);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mPageType = getArguments().getInt(KEY_PAGE_TYPE);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = FragmentDoubanMovieListBinding.inflate(inflater, container, false);
        mBinding.setViewmodel(getViewModel());
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI();

        getData();
    }

    private void initUI(){

        mEvent = new MovieListItemEvent();

        mAdapter = new BindingRecyclerAdapter<DoubanMovieItemEntity, MovieListItemVM>(BR.movieListItemVM,
                R.layout.item_movie_top_list,
                BR.movieListItemEvent,
                mEvent,
                MovieListItemVM.class);

        mBinding.movieTopListRV.setAdapter(mAdapter);

        mBinding.movieTopListRV.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.movieTopListRV.setHasFixedSize(true);

        //设置上拉事件
        mBinding.movieTopListERL.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getData();
            }

            @Override
            public void onRefreshing() {

            }
        });

        mBinding.movieTopListERL.setEnablePullToRefresh(false);
        mBinding.movieTopListERL.setLoadMoreModel(LoadModel.COMMON_MODEL);

        //数据请求之后，关闭上拉状态
        getViewModel().getMovieListShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean showLoading) {
                if (!showLoading) {
                    mBinding.movieTopListERL.closeLoadView();
                }
            }
        });


        //没过更多数据的时候，上拉刷新不可用
        getViewModel().getNoMoreData().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    mBinding.movieTopListERL.setLoadMoreModel(LoadModel.NONE);
                }
            }
        });
    }

    /**
     * 根据不同的页面类型，发起对应的网络请求
     */
    private void getData(){
        switch (mPageType){
            case PAGE_TYPE_IN_THEATERS:
                getViewModel().getMovieInTheaters();
                break;

            case PAGE_TYPE_COMING_SOON:
                getViewModel().getMovieComingSoon();
                break;

            case PAGE_TYPE_TOP_250:
                getViewModel().getMovieTop();
                break;
        }
    }

    public DoubanMovieListVM getViewModel(){
        if (mViewModel == null) {
            mViewModel = ViewModelProviders.of(this).get(DoubanMovieListVM.class);
        }
        return mViewModel;
    }

}
