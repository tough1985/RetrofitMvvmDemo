package me.xiba.startlearnmvvm.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by liukun on 2017/12/21.
 * 豆瓣影人简介
 */

public class DoubanMovieCastEntity implements Parcelable{

    private String id;
    private String name;
    private DoubanMovieImageEntity avatars;

    public DoubanMovieCastEntity() {
    }

    protected DoubanMovieCastEntity(Parcel in) {
        id = in.readString();
        name = in.readString();
        avatars = in.readParcelable(DoubanMovieImageEntity.class.getClassLoader());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DoubanMovieImageEntity getAvatars() {
        return avatars;
    }

    public void setAvatars(DoubanMovieImageEntity avatars) {
        this.avatars = avatars;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeParcelable(avatars, flags);
    }

    public static final Creator<DoubanMovieCastEntity> CREATOR = new Creator<DoubanMovieCastEntity>() {
        @Override
        public DoubanMovieCastEntity createFromParcel(Parcel in) {
            return new DoubanMovieCastEntity(in);
        }

        @Override
        public DoubanMovieCastEntity[] newArray(int size) {
            return new DoubanMovieCastEntity[size];
        }
    };
}
