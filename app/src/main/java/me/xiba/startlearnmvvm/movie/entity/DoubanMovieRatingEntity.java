package me.xiba.startlearnmvvm.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liukun on 2017/12/21.
 * 豆瓣电影评分
 */
public class DoubanMovieRatingEntity implements Parcelable{

    private float max;
    private float min;
    private float average;
    private String stars;

    public DoubanMovieRatingEntity() {
    }

    protected DoubanMovieRatingEntity(Parcel in) {
        max = in.readFloat();
        min = in.readFloat();
        average = in.readFloat();
        stars = in.readString();
    }

    public static final Creator<DoubanMovieRatingEntity> CREATOR = new Creator<DoubanMovieRatingEntity>() {
        @Override
        public DoubanMovieRatingEntity createFromParcel(Parcel in) {
            return new DoubanMovieRatingEntity(in);
        }

        @Override
        public DoubanMovieRatingEntity[] newArray(int size) {
            return new DoubanMovieRatingEntity[size];
        }
    };

    public float getMax() {
        return max;
    }

    public void setMax(float max) {
        this.max = max;
    }

    public float getMin() {
        return min;
    }

    public void setMin(float min) {
        this.min = min;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }

    public String getStars() {
        return stars;
    }

    public void setStars(String stars) {
        this.stars = stars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeFloat(max);
        dest.writeFloat(min);
        dest.writeFloat(average);
        dest.writeString(stars);
    }
}
