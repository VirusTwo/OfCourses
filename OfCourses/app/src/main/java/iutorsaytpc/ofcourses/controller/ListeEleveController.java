package iutorsaytpc.ofcourses.controller;

import android.view.View;
import iutorsaytpc.ofcourses.view.ListeElevesView;
import android.content.DialogInterface;
import android.view.View;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.view.ListeElevesView;


/**
 * Created by Cyril on 25/03/2016.
 */
public class ListeEleveController implements View.OnClickListener {
    private ListeElevesView view;
    public ListeEleveController(ListeElevesView view){
        this.view = view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addAppreciation:
                view.participationDialog();
                break;
            case R.id.addNote:
                view.marksDialog();
                break;
            case R.id.modifierButton:
                view.switchallSwitch();
                break;
        }
    }
}
