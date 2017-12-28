package me.xiba.startlearnmvvm.movie.viewmodel;

import android.databinding.ObservableField;
import android.util.Log;

import java.util.ArrayList;

import me.xiba.startlearnmvvm.base.BaseViewModel;
import me.xiba.startlearnmvvm.http.LoadingObserver;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieItemEntity;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieListResultEntity;
import me.xiba.startlearnmvvm.movie.model.MovieModel;

/**
 * @Author: liukun
 * @Date: 2017-12-25 16:34:04
 * @Description: 豆瓣电影列表 VM
 */
public class MovieTopListVM extends BaseViewModel {

    public static final String TAG = MovieTopListVM.class.getSimpleName();

    private MovieModel mModel;

    private int mPage = 0;

    public final ObservableField<ArrayList<DoubanMovieItemEntity>> mMovieItemOf = new ObservableField<>();

    private ArrayList<DoubanMovieItemEntity> mMovieItem;

    //LiveData 用来控制是否显示Loading对话框
    protected final SingleLiveEvent<Boolean> mMovieListShowLoading = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getMovieListShowLoading() {
        return mMovieListShowLoading;
    }

    //豆瓣电影详情网络请求回调接口
    private LoadingObserver<DoubanMovieListResultEntity> mDoubanTopMovieListObserver;

    public MovieTopListVM() {
        mModel = new MovieModel();

        mDoubanTopMovieListObserver = new LoadingObserver<>(new LoadingObserver.ObserverOnNextListener<DoubanMovieListResultEntity>() {
            @Override
            public void observerOnNext(DoubanMovieListResultEntity value) {
                mPage++;

                if (mMovieItem == null) {
                    mMovieItem = new ArrayList<>();
                }
                mMovieItem.addAll(value.getSubjects());

                mMovieItemOf.set(mMovieItem);
                mMovieItemOf.notifyChange();

                Log.e(TAG, "mDoubanTopMovieListObserver");

            }
        }, new LoadingObserver.ObserverOnErrorListener() {
            @Override
            public void observerOnError(String errorMsg) {

            }
        }, mMovieListShowLoading);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

    public void loadMore(){
        mModel.getDoubanTopList(mPage * 10, mDoubanTopMovieListObserver);
    }
}
