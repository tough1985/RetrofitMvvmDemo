package me.xiba.startlearnmvvm.difftype.viewmodel;

import me.xiba.startlearnmvvm.difftype.entity.DiffTypeUiBEntity;
import me.xiba.startlearnmvvm.difftype.entity.TypeB;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:48
 * @Description:当使用数据类型TypeB的时候，所需要的ViewModel，
 * 需要提供一个将TypeB转换成对应的UiEntity的包装类型DiffTypeUiBEntity
 * XML文件通过DiffTypeUiBEntity的内容做数据绑定
 */
public class DiffTypeItemBVM extends DiffTypeItemVM<TypeB> {

    private DiffTypeUiBEntity mUiBEntity;

    public DiffTypeItemBVM() {
        mUiBEntity = new DiffTypeUiBEntity();
    }

    @Override
    public void setData(TypeB typeB) {
        mUiBEntity.setT(typeB);
        uiDataOf.set(mUiBEntity);
        uiDataOf.notifyChange();
    }
}
