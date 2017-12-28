package me.xiba.startlearnmvvm.difftype.viewmodel;

import android.databinding.ObservableField;

import java.util.ArrayList;

import me.xiba.startlearnmvvm.base.BaseViewModel;
import me.xiba.startlearnmvvm.difftype.entity.TypeA;
import me.xiba.startlearnmvvm.difftype.entity.TypeB;
import me.xiba.startlearnmvvm.liveevent.SingleLiveEvent;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:12
 * @Description: fragment_diff_type.xml所对应的ViewModel，根据数据类型（TypeA或TypeB）来提供对应类型的数据
 */
public class DiffTypeVM<T> extends BaseViewModel {

    public final ObservableField<ArrayList> dataOf = new ObservableField<>();

    private ArrayList arrayList;

    private Class<T> tClass;

    private int mPage;

    //LiveData 用来控制是否显示Loading对话框
    protected final SingleLiveEvent<Boolean> mLoading = new SingleLiveEvent<>();

    public SingleLiveEvent<Boolean> getLoading() {
        return mLoading;
    }

    public DiffTypeVM(Class<T> tClass) {
        this.tClass = tClass;
        arrayList = new ArrayList<>();
        mPage = 0;
    }

    public void getData(){

        mLoading.setValue(true);
        if (tClass.isAssignableFrom(TypeA.class)) {
            TypeA typeA;
            for (int i = 0; i < 10; i++) {
                typeA = new TypeA();
                typeA.setUserAge(10 + i + mPage * 10);
                typeA.setUserName("TypeA name" + (i + mPage * 10));
                arrayList.add((T) typeA);
            }
        } else if (tClass.isAssignableFrom(TypeB.class)) {
            TypeB typeB;
            for (int i = 0; i < 10; i++) {
                typeB = new TypeB();
                typeB.setAge(10 + i + mPage * 10);
                typeB.setName("TypeB name" + (i + mPage * 10));
                arrayList.add((T) typeB);
            }
        }
        dataOf.set(arrayList);
        dataOf.notifyChange();

        mLoading.setValue(false);

        mPage++;
    }
}
