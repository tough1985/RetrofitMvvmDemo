package me.xiba.startlearnmvvm.movie.service;

import io.reactivex.Observable;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieDetailEntity;
import me.xiba.startlearnmvvm.movie.entity.DoubanMovieListResultEntity;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by liukun on 2017/12/21.
 */

public interface DoubanMovieService {

    /**
     * 获取豆瓣电影详情
     * @param movieId
     * @return
     */
    @GET("/v2/movie/subject/{movieId}")
    Observable<DoubanMovieDetailEntity> getDoubanMovieDetail(@Path("movieId") String movieId);

    /**
     * 获取豆瓣电影Top250列表
     * @param start
     * @param count
     * @return
     */
    @GET("/v2/movie/top250")
    Observable<DoubanMovieListResultEntity> getDoubanTopList(@Query("start") int start, @Query("count") int count);

    /**
     * 获取豆瓣电影正在热映列表
     * @return
     */
    @GET("/v2/movie/in_theaters")
    Observable<DoubanMovieListResultEntity> getMovieInTheaters(@Query("city") String city);

    /**
     * 获取豆瓣电影即将上映列表
     * @return
     */
    @GET("/v2/movie/coming_soon")
    Observable<DoubanMovieListResultEntity> getMovieComingSoon(@Query("start") int start, @Query("count") int count);

}
