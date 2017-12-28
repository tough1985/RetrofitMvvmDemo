package me.xiba.startlearnmvvm.movie.activity;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.base.BaseActivity;
import me.xiba.startlearnmvvm.databinding.ActivityMovieDetailBinding;
import me.xiba.startlearnmvvm.movie.util.MovieConstant;
import me.xiba.startlearnmvvm.movie.viewmodel.MovieDetailVM;

/**
 * Created by liukun on 2017/12/21.
 */

public class MovieDetailActivity extends BaseActivity {

    private String mMovieId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);
        binding.setViewmodel(getViewModel());

        mMovieId = getIntent().getStringExtra(MovieConstant.KEY_DOUBAN_MOVIE_ID);

        if (TextUtils.isEmpty(mMovieId)) {
            mMovieId = "26862829";
        }

        getViewModel().getMovieDetail(mMovieId);

        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected MovieDetailVM getViewModel() {
        return ViewModelProviders.of(this).get(MovieDetailVM.class);
    }
}
