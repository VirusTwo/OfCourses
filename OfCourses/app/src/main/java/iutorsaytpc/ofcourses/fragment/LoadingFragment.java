package iutorsaytpc.ofcourses.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.LoadingView;

/**
 * Created by VirusTwoIUT on 11/03/2016.
 */
@SuppressLint("ValidFragment")
public class LoadingFragment extends Fragment {
    private View view;

    public LoadingFragment(LoadingView view) {
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.view;
    }

}
