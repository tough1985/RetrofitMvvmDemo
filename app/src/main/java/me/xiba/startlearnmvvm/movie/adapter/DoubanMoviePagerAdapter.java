package me.xiba.startlearnmvvm.movie.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import me.xiba.startlearnmvvm.movie.fragment.DoubanMovieListFragment;

/**
 * @Author:liukun
 * @Date: 2017-12-27 18:01
 * @Description:
 */
public class DoubanMoviePagerAdapter extends FragmentStatePagerAdapter {
    private Map<Integer, SoftReference<Fragment>> mapFragment = new HashMap<Integer, SoftReference<Fragment>>();

    public DoubanMoviePagerAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        if (mapFragment.containsKey(position)) {
            SoftReference<Fragment> softReference = mapFragment.get(position);
            if (null != softReference) {
                fragment = softReference.get();
            }
        } else {
            switch (position) {
                case 0:
                    fragment = DoubanMovieListFragment.newInstance(DoubanMovieListFragment.PAGE_TYPE_IN_THEATERS);
                    break;
                case 1:
                    fragment = DoubanMovieListFragment.newInstance(DoubanMovieListFragment.PAGE_TYPE_COMING_SOON);
                    break;
                case 2:
                    fragment = DoubanMovieListFragment.newInstance(DoubanMovieListFragment.PAGE_TYPE_TOP_250);
                    break;
            }
            mapFragment.put(position, new SoftReference<Fragment>(fragment));
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
