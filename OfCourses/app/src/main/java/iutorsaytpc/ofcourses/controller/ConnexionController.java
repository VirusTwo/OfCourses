package iutorsaytpc.ofcourses.controller;

import android.view.View;
import android.widget.Toast;

import iutorsaytpc.ofcourses.MainActivity;
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
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String lol = BD.getNomClasse(1);
                        System.out.println(lol);
                        int resultConnexion  = BD.isLogin(view.getLogin(),view.getPassword());
                        System.out.println(resultConnexion);
                        Toast.makeText(view.getContext(),"TESt",Toast.LENGTH_SHORT).show();
                    }
                }).start();

                break;
        }
    }
}
