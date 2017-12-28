package me.xiba.startlearnmvvm.difftype.entity;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:16
 * @Description: 为了复用item_diff_type.xml所封装的UI包装类；
 * 其中的抽象方法是为了定义XML中的UI控件渲染所需要的数据而提供的方法，
 * 由子类根据数据类型去写具体实现
 */
public abstract class DiffTypeUiEntity<T> {

    //具体的数据类型由子类定义
    protected T data;

    public T getT() {
        return data;
    }

    public void setT(T data) {
    }

    //XML中「名称」控件需要显示的文本内容
    public abstract String getUiName();

    //XML中「年龄」控件需要显示的文本内容
    public abstract String getUiAge();

}
