package me.xiba.startlearnmvvm.difftype.factory;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import me.xiba.startlearnmvvm.difftype.entity.TypeA;
import me.xiba.startlearnmvvm.difftype.entity.TypeB;
import me.xiba.startlearnmvvm.difftype.viewmodel.DiffTypeVM;

/**
 * @Author:liukun
 * @Date: 2017-12-27 15:41
 * @Description:
 */
public class DiffTypeViewModelFactory extends ViewModelProvider.NewInstanceFactory{

    private Class<?> dClass;
    public DiffTypeViewModelFactory(Class<?> dClass) {
        this.dClass = dClass;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {

        if (dClass.isAssignableFrom(TypeA.class)) {
            return (T) new DiffTypeVM<TypeA>(TypeA.class);
        } else if (dClass.isAssignableFrom(TypeB.class)) {
            return (T) new DiffTypeVM<TypeB>(TypeB.class);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
