package iutorsaytpc.ofcourses.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import iutorsaytpc.ofcourses.view.ListeGroupesView;

/**
 * Created by guillaumemartinez on 30/03/2016
 */
@SuppressLint("ValidFragment")
public class ListeGroupeFragment extends Fragment {
    private ListeGroupesView view;

    public ListeGroupeFragment(ListeGroupesView view) {
        this.view = view;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return this.view;
    }
}
