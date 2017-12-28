package me.xiba.startlearnmvvm.difftype.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

import me.xiba.startlearnmvvm.difftype.fragment.DiffTypeAFragment;
import me.xiba.startlearnmvvm.difftype.fragment.DiffTypeBFragment;

/**
 * @Author:liukun
 * @Date: 2017-12-27 11:39
 * @Description:
 */
public class DiffTypePagerAdapter extends FragmentStatePagerAdapter {

    private Map<Integer, SoftReference<Fragment>> mapFragment = new HashMap<Integer, SoftReference<Fragment>>();

    public DiffTypePagerAdapter(FragmentManager fm) {
        super(fm);
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
                    fragment = new DiffTypeAFragment();
                    break;
                case 1:
                    fragment = new DiffTypeBFragment();
                    break;
            }
            mapFragment.put(position, new SoftReference<Fragment>(fragment));
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
