package me.xiba.startlearnmvvm.difftype.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:14
 * @Description: TypeA中的变量都带有user前缀，TypeB中的变量没有前缀
 */
public class TypeB implements Parcelable{

    private String name;
    private int age;

    public TypeB() {
    }

    protected TypeB(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }

    public static final Creator<TypeB> CREATOR = new Creator<TypeB>() {
        @Override
        public TypeB createFromParcel(Parcel in) {
            return new TypeB(in);
        }

        @Override
        public TypeB[] newArray(int size) {
            return new TypeB[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);
    }
}
