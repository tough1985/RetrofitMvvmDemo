package me.xiba.startlearnmvvm.difftype.viewmodel;

import me.xiba.startlearnmvvm.difftype.entity.DiffTypeUiAEntity;
import me.xiba.startlearnmvvm.difftype.entity.TypeA;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:45
 * @Description: 当使用数据类型TypeA的时候，所需要的ViewModel，
 * 需要提供一个将TypeA转换成对应的UiEntity的包装类型DiffTypeUiAEntity
 * XML文件通过DiffTypeUiAEntity的内容做数据绑定
 */
public class DiffTypeItemAVM extends DiffTypeItemVM<TypeA> {

    private DiffTypeUiAEntity mUiAEntity;

    public DiffTypeItemAVM() {
        mUiAEntity = new DiffTypeUiAEntity();
    }

    @Override
    public void setData(TypeA typeA) {
        mUiAEntity.setT(typeA);
        uiDataOf.set(mUiAEntity);
        uiDataOf.notifyChange();
    }
}
