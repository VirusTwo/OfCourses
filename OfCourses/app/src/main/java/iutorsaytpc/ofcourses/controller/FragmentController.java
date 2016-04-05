package iutorsaytpc.ofcourses.controller;

import android.view.View;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.FragmentView;

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
                //load edt
                break;
            case R.id.listeGroupe:
                //load listeGroupe
                break;
        }
    }
}
