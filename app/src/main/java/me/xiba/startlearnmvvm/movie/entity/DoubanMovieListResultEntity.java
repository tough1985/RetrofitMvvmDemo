package me.xiba.startlearnmvvm.movie.entity;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by liukun on 2017/12/25.
 */

public class DoubanMovieListResultEntity implements Parcelable {

    private int start;
    private int count;
    private int total;
    private String title;
    private ArrayList<DoubanMovieItemEntity> subjects;

    public DoubanMovieListResultEntity() {
    }

    protected DoubanMovieListResultEntity(Parcel in) {
        start = in.readInt();
        count = in.readInt();
        total = in.readInt();
        title = in.readString();
        subjects = in.createTypedArrayList(DoubanMovieItemEntity.CREATOR);
    }

    public static final Creator<DoubanMovieListResultEntity> CREATOR = new Creator<DoubanMovieListResultEntity>() {
        @Override
        public DoubanMovieListResultEntity createFromParcel(Parcel in) {
            return new DoubanMovieListResultEntity(in);
        }

        @Override
        public DoubanMovieListResultEntity[] newArray(int size) {
            return new DoubanMovieListResultEntity[size];
        }
    };

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<DoubanMovieItemEntity> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<DoubanMovieItemEntity> subjects) {
        this.subjects = subjects;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(start);
        dest.writeInt(count);
        dest.writeInt(total);
        dest.writeString(title);
        dest.writeTypedList(subjects);
    }
}
