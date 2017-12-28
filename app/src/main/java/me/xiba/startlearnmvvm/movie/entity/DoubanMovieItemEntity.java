package me.xiba.startlearnmvvm.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/12/25.
 */

public class DoubanMovieItemEntity implements Parcelable{

    private String id;
    //名称
    private String title;
    //上映年
    private String year;
    //导演列表
    private ArrayList<DoubanMovieCastEntity> directors;
    //演员列表
    private ArrayList<DoubanMovieCastEntity> casts;
    //类型
    private ArrayList<String> genres;
    //图片
    private DoubanMovieImageEntity images;
    //评分
    private DoubanMovieRatingEntity rating;

    public DoubanMovieItemEntity() {
    }

    protected DoubanMovieItemEntity(Parcel in) {
        id = in.readString();
        title = in.readString();
        year = in.readString();
        directors = in.createTypedArrayList(DoubanMovieCastEntity.CREATOR);
        casts = in.createTypedArrayList(DoubanMovieCastEntity.CREATOR);
        genres = in.createStringArrayList();
        images = in.readParcelable(DoubanMovieImageEntity.class.getClassLoader());
        rating = in.readParcelable(DoubanMovieRatingEntity.class.getClassLoader());
    }

    public static final Creator<DoubanMovieItemEntity> CREATOR = new Creator<DoubanMovieItemEntity>() {
        @Override
        public DoubanMovieItemEntity createFromParcel(Parcel in) {
            return new DoubanMovieItemEntity(in);
        }

        @Override
        public DoubanMovieItemEntity[] newArray(int size) {
            return new DoubanMovieItemEntity[size];
        }
    };

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public ArrayList<DoubanMovieCastEntity> getDirectors() {
        return directors;
    }

    public void setDirectors(ArrayList<DoubanMovieCastEntity> directors) {
        this.directors = directors;
    }

    public ArrayList<DoubanMovieCastEntity> getCasts() {
        return casts;
    }

    public void setCasts(ArrayList<DoubanMovieCastEntity> casts) {
        this.casts = casts;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public DoubanMovieImageEntity getImages() {
        return images;
    }

    public void setImages(DoubanMovieImageEntity images) {
        this.images = images;
    }

    public DoubanMovieRatingEntity getRating() {
        return rating;
    }

    public void setRating(DoubanMovieRatingEntity rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(year);
        dest.writeTypedList(directors);
        dest.writeTypedList(casts);
        dest.writeStringList(genres);
        dest.writeParcelable(images, flags);
        dest.writeParcelable(rating, flags);
    }

    /**
     * 获取导演名单
     * @return
     */
    public String getDirectorNames(){
        if (directors == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (DoubanMovieCastEntity cast : directors) {
            sb.append(cast.getName() + "/");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    /**
     * 获取演员名单
     * @return
     */
    public String getCastNames(){
        if (casts == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (DoubanMovieCastEntity cast : casts) {
            sb.append(cast.getName() + " ");
        }

        return sb.toString().trim();
    }
}
