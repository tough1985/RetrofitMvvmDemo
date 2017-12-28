package me.xiba.startlearnmvvm.movie.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import me.xiba.startlearnmvvm.base.BaseViewModel;
import me.xiba.startlearnmvvm.http.LoadingObserver;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieDetailEntity;
import me.xiba.startlearnmvvm.movie.model.MovieModel;
import me.xiba.startlearnmvvm.util.StringUtil;

/**
 * Created by liukun on 2017/12/21.
 */

public class MovieDetailVM extends BaseViewModel {

    public static final String TAG = MovieDetailVM.class.getSimpleName();

    private MovieModel mModel;

    //页面数据源
    public final ObservableField<DoubanMovieDetailEntity> mDoubanMovieDetail = new ObservableField<>();

    //电影上映年份
    public final ObservableField<String> mMovieYear = new ObservableField<>();

    //电影导演名单
    public final ObservableField<String> mMovieDirectorNames = new ObservableField<>();

    //电影演员名单
    public final ObservableField<String> mMovieCastNames = new ObservableField<>();

    //电影类型列表
    public final ObservableField<String> mMovieGenres = new ObservableField<>();

    //电影国家列表
    public final ObservableField<String> mMovieCountries = new ObservableField<>();

    //豆瓣电影详情网络请求回调接口
    private LoadingObserver<DoubanMovieDetailEntity> mDoubanMovieDetailObserver;


    public MovieDetailVM() {
        mModel = new MovieModel();

        mDoubanMovieDetailObserver = new LoadingObserver<>(new LoadingObserver.ObserverOnNextListener<DoubanMovieDetailEntity>() {
            @Override
            public void observerOnNext(DoubanMovieDetailEntity value) {
                mDoubanMovieDetail.set(value);

                mMovieYear.set("年份：" + value.getYear());

                mMovieDirectorNames.set("导演：" + value.getDirectorNames());

                mMovieCastNames.set("主演：" + value.getCastNames());

                mMovieGenres.set("类型：" + StringUtil.joinItem(value.getGenres()));

                mMovieCountries.set("国家：" + StringUtil.joinItem(value.getCountries()));

            }
        }, new LoadingObserver.ObserverOnErrorListener() {
            @Override
            public void observerOnError(String errorMsg) {

            }
        }, mShowLoading);
    }

    /**
     * 获取电影详情
     * @param movieId
     */
    public void getMovieDetail(String movieId){
        mModel.getDoubanMovieDetail(movieId, mDoubanMovieDetailObserver);
    }



    @Override
    protected void onCleared() {

        if (mDoubanMovieDetailObserver != null) {
            mDoubanMovieDetailObserver.cancelRequest();
        }
        super.onCleared();
    }
}
