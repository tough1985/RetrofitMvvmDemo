package me.xiba.startlearnmvvm.difftype.entity;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:19
 * @Description: DiffTypeUiEntity的子类，提供了TypeA数据类型与item_diff_type.xml的映射关系
 */
public class DiffTypeUiAEntity extends DiffTypeUiEntity<TypeA> {

    @Override
    public TypeA getT() {
        return data;
    }

    @Override
    public void setT(TypeA typeA) {
        data = typeA;
    }

    @Override
    public String getUiName() {
        return data == null ? "" : data.getUserName();
    }

    @Override
    public String getUiAge() {
        return data == null ? "" : String.valueOf(data.getUserAge());
    }

}
