package me.xiba.startlearnmvvm.movie.viewmodel;

import android.databinding.ObservableField;

import me.xiba.startlearnmvvm.base.BaseAdapterViewModel;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieItemEntity;

/**
 * Created by liukun on 2017/12/26.
 */

public class MovieListItemVM extends BaseAdapterViewModel<DoubanMovieItemEntity> {

    public final ObservableField<DoubanMovieItemEntity> doubanMovieItem = new ObservableField<>();

    @Override
    public void setData(DoubanMovieItemEntity itemEntity) {

        doubanMovieItem.set(itemEntity);
        doubanMovieItem.notifyChange();

    }

}
