package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.modele.ClasseSingleton;
import iutorsaytpc.ofcourses.modele.MatiereSingleton;
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
                RelativeLayout content = ((RelativeLayout) ((Activity) view.getContext()).findViewById(R.id.layoutParent));
                content.removeAllViews();
                content.addView(new ListeElevesView(view.getContext()));
                ClasseSingleton.setId_classe(view.getSelectedIdClasse());
                MatiereSingleton.setId_matiere(view.getSelectedIdMatiere());
                break;
        }
    }
}