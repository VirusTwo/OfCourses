package iutorsaytpc.ofcourses.controller;

import android.content.DialogInterface;
import android.view.View;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.view.ConnexionView;

/**
 * Created by VirusTwoIUT on 11/03/2016.
 */
public class ConnexionController implements View.OnClickListener {

    private ConnexionView view;

    public ConnexionController(ConnexionView view){
        this.view = view;
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.connexion:
                String lol = BD.getNomClasse(1);
                break;
        }
    }
}
