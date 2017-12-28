package me.xiba.startlearnmvvm.difftype.entity;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:23
 * @Description: DiffTypeUiEntity的子类，提供了TypeB数据类型与item_diff_type.xml的映射关系
 */
public class DiffTypeUiBEntity extends DiffTypeUiEntity<TypeB> {

    @Override
    public TypeB getT() {
        return data;
    }

    @Override
    public void setT(TypeB typeB) {
        data = typeB;
    }

    @Override
    public String getUiName() {
        return data == null ? "" : data.getName();
    }

    @Override
    public String getUiAge() {
        return data == null ? "" : String.valueOf(data.getAge());
    }
}
