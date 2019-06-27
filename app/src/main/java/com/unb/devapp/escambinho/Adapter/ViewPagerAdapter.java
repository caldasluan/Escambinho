package com.unb.devapp.escambinho.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.unb.devapp.escambinho.Fragment.ChatFragment;
import com.unb.devapp.escambinho.Fragment.EscambinhoFragment;
import com.unb.devapp.escambinho.Fragment.HistoricFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return EscambinhoFragment.newInstance();
            case 1:
                return HistoricFragment.newInstance();
            case 2:
                return ChatFragment.newInstance();
            default:
                return EscambinhoFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
