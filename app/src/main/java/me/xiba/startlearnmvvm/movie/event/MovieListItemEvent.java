package me.xiba.startlearnmvvm.movie.event;

import android.content.Context;
import android.content.Intent;
import android.util.SparseArray;
import android.view.View;

import me.xiba.startlearnmvvm.base.BaseEvent;
import me.xiba.startlearnmvvm.movie.activity.MovieDetailActivity;
import me.xiba.startlearnmvvm.movie.util.MovieConstant;
import me.xiba.startlearnmvvm.movie.viewmodel.MovieListItemVM;

/**
 * Created by liukun on 2017/12/26.
 */

public class MovieListItemEvent extends BaseEvent<MovieListItemVM>{

    @Override
    public void onClick(View view, MovieListItemVM movieListItemVM) {

        Context context = view.getContext();
        if (context != null) {

            Intent i = new Intent(context, MovieDetailActivity.class);
            i.putExtra(MovieConstant.KEY_DOUBAN_MOVIE_ID, movieListItemVM.doubanMovieItem.get().getId());

            context.startActivity(i);
        }
    }
}
