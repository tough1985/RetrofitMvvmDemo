package me.xiba.startlearnmvvm.movie.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import me.xiba.startlearnmvvm.R;
import me.xiba.startlearnmvvm.movie.adapter.DoubanMoviePagerAdapter;

/**
 * @Author:liukun
 * @Date: 2017-12-27 17:58
 * @Description:
 */
public class DoubanMovieActivity extends AppCompatActivity {

    private TabLayout mDoubanMovieTL;
    private ViewPager mDoubanMovieVP;

    private DoubanMoviePagerAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_douban_movie);

        mDoubanMovieTL = findViewById(R.id.douban_movie_TL);
        mDoubanMovieVP = findViewById(R.id.douban_movie_VP);

        initTab();

        mAdapter = new DoubanMoviePagerAdapter(getSupportFragmentManager());
        mDoubanMovieVP.setAdapter(mAdapter);

        mDoubanMovieVP.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mDoubanMovieTL));
    }

    private void initTab(){
        mDoubanMovieTL.addTab(mDoubanMovieTL.newTab().setText("正在热映"));
        mDoubanMovieTL.addTab(mDoubanMovieTL.newTab().setText("即将上映"));
        mDoubanMovieTL.addTab(mDoubanMovieTL.newTab().setText("Top250"));

        mDoubanMovieTL.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mDoubanMovieVP.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
