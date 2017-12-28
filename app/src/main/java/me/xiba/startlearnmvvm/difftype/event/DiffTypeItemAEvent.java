package me.xiba.startlearnmvvm.difftype.event;

import android.view.View;
import android.widget.Toast;

import me.xiba.startlearnmvvm.MyApplication;
import me.xiba.startlearnmvvm.base.BaseEvent;
import me.xiba.startlearnmvvm.difftype.viewmodel.DiffTypeItemAVM;

/**
 * @Author:liukun
 * @Date: 2017-12-27 14:53
 * @Description: 当数据类型是TypeA时，item_diff_type.xml所对应的事件实现
 */
public class DiffTypeItemAEvent extends BaseEvent<DiffTypeItemAVM> {

    @Override
    public void onClick(View view, DiffTypeItemAVM diffTypeItemAVM) {
        Toast.makeText(MyApplication.getInstance(), "This is from Event A", Toast.LENGTH_SHORT).show();
    }
}
