package hcmute.spkt.Foody_18.controller;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import hcmute.spkt.Foody_18.fragment.AdsFragment1;
import hcmute.spkt.Foody_18.fragment.AdsFragment2;
import hcmute.spkt.Foody_18.fragment.AdsFragment3;

public class FragmentBottomNavigation extends FragmentPagerAdapter {

    private final int numberPage = 3;


    public FragmentBottomNavigation(@NonNull FragmentManager fragmentManager, int behavior) {
        super(fragmentManager, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new AdsFragment1();
            case 1:
                return new AdsFragment2();
            case 2:
                return new AdsFragment3();

            default:
                return new AdsFragment1();
        }
    }

    @Override
    public int getCount() {
        return numberPage;
    }
}