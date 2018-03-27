package com.codegene.femicodes.drugreminder.adapters;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.codegene.femicodes.drugreminder.fragments.TabFragment;
import com.codegene.femicodes.drugreminder.R;
import com.codegene.femicodes.drugreminder.models.Reminder;

public class ViewPageAdapter extends FragmentPagerAdapter implements PagerSlidingTabStrip.CustomTabProvider {

//    private final int[] ICONS = {
//            R.drawable.selector_icon_active,
//            R.drawable.selector_icon_inactive
//    };

    private final String[] TITLES = {"Reminders", "History"};

    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public void tabUnselected(View view) {
        view.setSelected(false);
    }

    @Override
    public void tabSelected(View view) {
        view.setSelected(true);
    }

    @Override
    public View getCustomTabView(ViewGroup parent, int position) {
        FrameLayout customLayout = (FrameLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_tab, parent, false);
        ((TextView) customLayout.findViewById(R.id.image)).setText(TITLES[position]);
        return customLayout;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }

    @Override
    public Fragment getItem(int position) {
        Bundle bundle = new Bundle();
        switch (position) {
            case 0:
            default:
                bundle.putInt("TYPE", Reminder.ACTIVE);
                break;
            case 1:
                bundle.putInt("TYPE", Reminder.INACTIVE);
                break;
        }
        Fragment fragment = new TabFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}