package me.xiba.startlearnmvvm.difftype.event;

import android.view.View;
import android.widget.Toast;

import me.xiba.startlearnmvvm.MyApplication;
import me.xiba.startlearnmvvm.base.BaseEvent;
import me.xiba.startlearnmvvm.difftype.viewmodel.DiffTypeItemBVM;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:55
 * @Description: 当数据类型是TypeB时，item_diff_type.xml所对应的事件实现
 */
public class DiffTypeItemBEvent extends BaseEvent<DiffTypeItemBVM> {

    @Override
    public void onClick(View view, DiffTypeItemBVM diffTypeItemBVM) {
        Toast.makeText(MyApplication.getInstance(), "This is from Event B", Toast.LENGTH_SHORT).show();
    }
}
