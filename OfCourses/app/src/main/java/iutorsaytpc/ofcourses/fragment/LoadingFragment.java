package iutorsaytpc.ofcourses.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutorsaytpc.ofcourses.R;

/**
 * Created by VirusTwoIUT on 11/03/2016.
 */
public class LoadingFragment extends Fragment {
    private View view;

    public LoadingFragment(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view= inflater.inflate(R.layout.loading_fragment,container,false);
        return this.view;
    }

}
