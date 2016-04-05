package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import iutorsaytpc.ofcourses.R;
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
        switch (v.getId()){
            case R.id.edt:

                break;
            case R.id.listeGroupe:
                FrameLayout content = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                content.removeAllViews();
                content.addView(new ListeGroupesView(view.getContext()));
                content.setContentDescription("ListeGroupView");
                break;
        }
    }
}
