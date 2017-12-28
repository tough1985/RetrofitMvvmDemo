package me.xiba.startlearnmvvm.movie.model;


import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.xiba.startlearnmvvm.http.RetrofitHolder;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieDetailEntity;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieListResultEntity;
import me.xiba.startlearnmvvm.movie.service.DoubanMovieService;

/**
 * Created by liukun on 2017/12/21.
 */

public class MovieModel {

    private volatile DoubanMovieService mService;

    public static final int PAGE_SIZE = 10;

    public MovieModel(){}

    public DoubanMovieService getService(){
        if(mService == null){
            synchronized (DoubanMovieService.class){
                if(mService == null){
                    mService = RetrofitHolder.getRetrofitInstance().create(DoubanMovieService.class);
                }
            }
        }

        return mService;
    }

    /**
     * 获取豆瓣电影详情
     * @param movieId 电影ID
     * @param observer
     */
    public void getDoubanMovieDetail(String movieId, Observer<DoubanMovieDetailEntity> observer){
        Observable observable = getService().getDoubanMovieDetail(movieId);

        makeSubscribe(observable, observer);
    }

    /**
     * 获取豆瓣电影Top250列表
     * @param start
     * @param observer
     */
    public void getDoubanTopList(int start, Observer<DoubanMovieListResultEntity> observer){
        Observable observable = getService().getDoubanTopList(start, PAGE_SIZE);

        makeSubscribe(observable, observer);
    }

    /**
     * 获取豆瓣电影正在热映列表
     * @param observer
     */
    public void getMovieInTheaters(Observer<DoubanMovieListResultEntity> observer){
        Observable observable = getService().getMovieInTheaters("上海");

        makeSubscribe(observable, observer);
    }

    /**
     * 获取豆瓣电影即将上映列表
     * @param observer
     */
    public void getMovieComingSoon(int start, Observer<DoubanMovieListResultEntity> observer){
        Observable observable = getService().getMovieComingSoon(start, PAGE_SIZE);

        makeSubscribe(observable, observer);
    }

    /**
     * 控制线程，并发起订阅
     * @param observable
     * @param observer
     * @param <T>
     */
    protected  <T> void makeSubscribe(Observable<T> observable, Observer<T> observer){
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }

}
