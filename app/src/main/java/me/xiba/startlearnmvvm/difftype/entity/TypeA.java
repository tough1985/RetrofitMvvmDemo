package me.xiba.startlearnmvvm.difftype.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:13
 * @Description: TypeA中的变量都带有user前缀，TypeB中的变量没有前缀
 */
public class TypeA implements Parcelable{

    private String userName;
    private int userAge;

    public TypeA() {
    }

    protected TypeA(Parcel in) {
        userName = in.readString();
        userAge = in.readInt();
    }

    public static final Creator<TypeA> CREATOR = new Creator<TypeA>() {
        @Override
        public TypeA createFromParcel(Parcel in) {
            return new TypeA(in);
        }

        @Override
        public TypeA[] newArray(int size) {
            return new TypeA[size];
        }
    };

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int userAge) {
        this.userAge = userAge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userName);
        dest.writeInt(userAge);
    }
}
