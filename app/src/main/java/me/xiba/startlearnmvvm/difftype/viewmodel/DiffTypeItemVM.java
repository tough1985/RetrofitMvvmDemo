package me.xiba.startlearnmvvm.difftype.viewmodel;

import android.databinding.ObservableField;

import me.xiba.startlearnmvvm.base.BaseAdapterViewModel;
import me.xiba.startlearnmvvm.difftype.entity.DiffTypeUiEntity;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:42
 * @Description: 当决定要复用一个XML，我们需要为该XML提供一个抽象的ViewModel与XML进行绑定，
 * XML中的UI应该根据uiDataOf的数据内容进行绑定刷新。
 * DiffTypeUiEntity中应该封装了XML所需要的所有UI状态所需要的数据。
 */
public abstract class DiffTypeItemVM<T> extends BaseAdapterViewModel<T> {
    public final ObservableField<DiffTypeUiEntity> uiDataOf = new ObservableField<>();
}
