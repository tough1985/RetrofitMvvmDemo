package me.xiba.startlearnmvvm.movie.viewmodel;

import android.databinding.ObservableField;

import java.util.ArrayList;

import me.xiba.startlearnmvvm.base.BaseViewModel;
import me.xiba.startlearnmvvm.http.LoadingObserver;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieItemEntity;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieListResultEntity;
import me.xiba.startlearnmvvm.movie.fragment.DoubanMovieListFragment;
import me.xiba.startlearnmvvm.movie.model.MovieModel;

/**
 * @Author:liukun
 * @Date: 2017-12-27 18:53
 * @Description:
 */
public class DoubanMovieListVM extends BaseViewModel {

    public static final String TAG = DoubanMovieListVM.class.getSimpleName();

    private MovieModel mModel;

    private int mPageType = 0;

    //分页索引
    private int mPage = 0;

    //数据源
    public final ObservableField<ArrayList<DoubanMovieItemEntity>> dataOf = new ObservableField<>();

    //豆瓣电影详情网络请求回调接口
    private LoadingObserver<DoubanMovieListResultEntity> mDoubanTopMovieListObserver;

    private ArrayList<DoubanMovieItemEntity> mMovieItem;

    //LiveData 用来控制是否显示Loading对话框
    protected final SingleLiveEvent<Boolean> mMovieListShowLoading = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getMovieListShowLoading() {
        return mMovieListShowLoading;
    }

    //通知View没有更多数据
    public final SingleLiveEvent<Boolean> mNoMoreData = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getNoMoreData() {
        return mNoMoreData;
    }

    public DoubanMovieListVM() {
        mModel = new MovieModel();

        mDoubanTopMovieListObserver = new LoadingObserver<>(new LoadingObserver.ObserverOnNextListener<DoubanMovieListResultEntity>() {
            @Override
            public void observerOnNext(DoubanMovieListResultEntity value) {

                mPage++;

                if (mMovieItem == null) {
                    mMovieItem = new ArrayList<>();
                }
                mMovieItem.addAll(value.getSubjects());

                dataOf.set(mMovieItem);
                dataOf.notifyChange();

                if (mPageType == DoubanMovieListFragment.PAGE_TYPE_IN_THEATERS
                        || ((mPageType == DoubanMovieListFragment.PAGE_TYPE_COMING_SOON
                            || mPageType == DoubanMovieListFragment.PAGE_TYPE_TOP_250)
                        && mPage > value.getTotal() / MovieModel.PAGE_SIZE)) {
                    mNoMoreData.setValue(true);
                }

            }
        }, new LoadingObserver.ObserverOnErrorListener() {
            @Override
            public void observerOnError(String errorMsg) {

            }
        }, mMovieListShowLoading);
    }

    /**
     * 获取豆瓣口碑榜
     */
    public void getMovieTop() {
        mPageType = DoubanMovieListFragment.PAGE_TYPE_TOP_250;
        mModel.getDoubanTopList(mPage * 10, mDoubanTopMovieListObserver);
    }

    /**
     * 获取正在热映列表
     */
    public void getMovieInTheaters() {
        mPageType = DoubanMovieListFragment.PAGE_TYPE_IN_THEATERS;
        mModel.getMovieInTheaters(mDoubanTopMovieListObserver);
    }

    /**
     * 获取即将上映列表
     */
    public void getMovieComingSoon() {
        mPageType = DoubanMovieListFragment.PAGE_TYPE_COMING_SOON;
        mModel.getMovieComingSoon(mPage * 10, mDoubanTopMovieListObserver);
    }
}
