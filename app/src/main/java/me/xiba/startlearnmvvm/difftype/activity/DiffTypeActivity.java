package me.xiba.startlearnmvvm.difftype.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.difftype.adapter.DiffTypePagerAdapter;

/**
 * @Author:liukun
 * @Date: 2017-12-27 11:17
 * @Description: 用来演示在RecyclerView使用了MVVM的情形下，通过复用XML文件，实现对不同的数据类型的支持
 */
public class DiffTypeActivity extends AppCompatActivity {

    private TabLayout mDiffTypeTL;
    private ViewPager mDiffTypeVP;

    private DiffTypePagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diff_type);

        mDiffTypeTL = findViewById(R.id.diff_type_TL);
        mDiffTypeVP = findViewById(R.id.diff_type_VP);

        initTab();

        mAdapter = new DiffTypePagerAdapter(getSupportFragmentManager());
        mDiffTypeVP.setAdapter(mAdapter);

        mDiffTypeVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDiffTypeTL));
    }

    private void initTab(){
        mDiffTypeTL.addTab(mDiffTypeTL.newTab().setText("Type A"));
        mDiffTypeTL.addTab(mDiffTypeTL.newTab().setText("Type B"));
    }
}
