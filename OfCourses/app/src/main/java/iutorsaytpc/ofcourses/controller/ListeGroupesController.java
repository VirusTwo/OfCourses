package iutorsaytpc.ofcourses.controller;

import android.view.View;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.view.ListeElevesView;
import iutorsaytpc.ofcourses.view.ListeGroupesView;

/**
 * Created by Cyril on 30/03/2016.
 */
public class ListeGroupesController implements View.OnClickListener {
    private ListeGroupesView view;
    public ListeGroupesController(ListeGroupesView view){
        this.view = view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.buttonSuivant:
                //Changer de vue (aller a la vue liste élèves
                break;
        }
    }
}