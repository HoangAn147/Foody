package hcmute.spkt.Foody_18.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import hcmute.spkt.Foody_18.R;

public class AdsFragment1 extends Fragment {

    public AdsFragment1() {
    }

    public AdsFragment1(int contentLayoutId) {
        super(contentLayoutId);
    }

    public static AdsFragment1 newInstance() {
        AdsFragment1 fragment = new AdsFragment1();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.ads_fragment1, container, false);
    }
}