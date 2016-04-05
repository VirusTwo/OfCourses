package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
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
                ClasseSingleton.setId_classe(view.getSelectedIdClasse());
                MatiereSingleton.setId_matiere(view.getSelectedIdMatiere());
                ClasseSingleton.setName_classe(view.getSelectedNameClasse());
                MatiereSingleton.setName_matiere(view.getSelectedNameMatiere());
                FrameLayout content = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                content.removeAllViews();
                content.addView(new ListeElevesView(view.getContext()));
                content.setContentDescription("ListeEleveView");
                break;
        }
    }
}