package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.FragmentView;
import iutorsaytpc.ofcourses.view.ListeGroupesView;
//import iutorsaytpc.ofcourses.view.EdtView;

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
                //changement de vue vers edt
                /*FrameLayout contentedt = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                contentedt.removeAllViews();
                contentedt.addView(new Edtview(view.getContext()));
                contentedt.setContentDescription("EditView");*/
                break;
            case R.id.listeGroupe:
                FrameLayout contentlg = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                contentlg.removeAllViews();
                contentlg.addView(new ListeGroupesView(view.getContext()));
                contentlg.setContentDescription("ListeGroupView");
                break;
        }
    }
}