package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.EdtView;
import iutorsaytpc.ofcourses.view.FragmentView;
import iutorsaytpc.ofcourses.view.ListeGroupesView;
import iutorsaytpc.ofcourses.view.EdtView;

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
                content = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                content.removeAllViews();
                content.addView(new EdtView(view.getContext()));
                break;
            case R.id.listeGroupe:
                content = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                content.removeAllViews();
                content.addView(new ListeGroupesView(view.getContext()));
                break;
        }
    }
}