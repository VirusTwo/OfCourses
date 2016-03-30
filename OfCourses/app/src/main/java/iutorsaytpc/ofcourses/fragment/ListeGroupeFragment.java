package iutorsaytpc.ofcourses.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutorsaytpc.ofcourses.view.LoadingView;

/**
 * Created by guillaumemartinez on 30/03/2016
 */
public class ListeGroupeFragment extends Fragment {
    private View view;

    public ListeGroupeFragment(Liste view) {
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.view;
    }
}
