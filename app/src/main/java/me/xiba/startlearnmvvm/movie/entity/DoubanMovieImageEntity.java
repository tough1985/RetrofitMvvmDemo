package me.xiba.startlearnmvvm.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liukun on 2017/12/21.
 * 豆瓣图片地址
 */
public class DoubanMovieImageEntity implements Parcelable{

    private String small;
    private String medium;
    private String large;

    public DoubanMovieImageEntity() {
    }

    protected DoubanMovieImageEntity(Parcel in) {
        small = in.readString();
        medium = in.readString();
        large = in.readString();
    }

    public static final Creator<DoubanMovieImageEntity> CREATOR = new Creator<DoubanMovieImageEntity>() {
        @Override
        public DoubanMovieImageEntity createFromParcel(Parcel in) {
            return new DoubanMovieImageEntity(in);
        }

        @Override
        public DoubanMovieImageEntity[] newArray(int size) {
            return new DoubanMovieImageEntity[size];
        }
    };

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small;
    }

    public String getMedium() {
        return medium;
    }

    public void setMedium(String medium) {
        this.medium = medium;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(small);
        dest.writeString(medium);
        dest.writeString(large);
    }
}
