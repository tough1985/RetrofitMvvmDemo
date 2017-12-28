package me.xiba.startlearnmvvm.movie.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;

import com.ajguan.library.EasyRefreshLayout;

import me.xiba.startlearnmvvm.BR;
import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.base.BaseActivity;
import me.xiba.startlearnmvvm.databinding.ActivityMovieTopListBinding;
import me.xiba.startlearnmvvm.movie.adapter.BindingRecyclerAdapter;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieItemEntity;
import me.xiba.startlearnmvvm.movie.event.MovieListItemEvent;
import me.xiba.startlearnmvvm.movie.viewmodel.MovieListItemVM;
import me.xiba.startlearnmvvm.movie.viewmodel.MovieTopListVM;

/**
 * @Author: liukun
 * @Date: 2017-12-25 16:34:04
 * @Description: 豆瓣电影列表
 */
public class MovieTopListActivity extends BaseActivity {

    public static final String TAG = MovieTopListActivity.class.getSimpleName();

    private MovieTopListVM mViewModel;
    private ActivityMovieTopListBinding mBinding;

    private BindingRecyclerAdapter mAdapter;

    private MovieListItemEvent mEvent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_movie_top_list);
        mBinding.setViewmodel(getViewModel());

        init();
        initUI();

        getViewModel().loadMore();
    }

    private void initUI(){

        mEvent = new MovieListItemEvent();

        mAdapter = new BindingRecyclerAdapter<DoubanMovieItemEntity, MovieListItemVM>(BR.movieListItemVM,
                R.layout.item_movie_top_list,
                BR.movieListItemEvent,
                mEvent,
                MovieListItemVM.class);

        mBinding.movieTopListRV.setAdapter(mAdapter);

        mBinding.movieTopListRV.setLayoutManager(new LinearLayoutManager(this));
        mBinding.movieTopListRV.setHasFixedSize(true);

//        mBinding.movieTopListERL.setLoadMoreView(getLayoutInflater().inflate(R.layout.item_load_more, mBinding.movieTopListRV));
        mBinding.movieTopListERL.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {
                getViewModel().loadMore();
            }

            @Override
            public void onRefreshing() {

            }
        });

        mBinding.movieTopListERL.setEnablePullToRefresh(false);

        getViewModel().getMovieListShowLoading().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean showLoading) {
                if (!showLoading) {
                    mBinding.movieTopListERL.closeLoadView();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public MovieTopListVM getViewModel(){
        if (mViewModel == null) {
            mViewModel = ViewModelProviders.of(this).get(MovieTopListVM.class);
        }
        return mViewModel;
    }
}
