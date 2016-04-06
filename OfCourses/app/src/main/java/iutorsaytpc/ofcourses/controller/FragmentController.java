package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import iutorsaytpc.ofcourses.MainActivity;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.EdtView;
import iutorsaytpc.ofcourses.view.FragmentView;
import iutorsaytpc.ofcourses.view.ListeElevesView;
import iutorsaytpc.ofcourses.view.ListeGroupesView;

/**
 * Created by Tilloman on 30/03/2016.
 */
public class FragmentController implements View.OnClickListener{

    private FragmentView view;

    public FragmentController(FragmentView view){
        this.view = view;
    }

    @Override
    public void onClick(View v) {
        FrameLayout content = null;
        switch (v.getId()){
            case R.id.edt:
                ((MainActivity) view.getContext()).setFragmentShowed(new EdtView(view.getContext()));
                break;
            case R.id.listeGroupe:

                ((MainActivity) view.getContext()).setFragmentShowed(new ListeGroupesView(view.getContext()));
                break;
        }
    }
}
