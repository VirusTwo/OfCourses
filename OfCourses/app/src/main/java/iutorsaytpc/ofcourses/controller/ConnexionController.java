package iutorsaytpc.ofcourses.controller;

import android.view.View;

import java.util.ArrayList;
import java.util.Objects;

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
                        ArrayList<Objects> lol = BD.getCours();
                        System.out.println(lol);
                        int resultConnexion  = BD.isLogin(view.getLogin(),view.getPassword());
                        System.out.println(resultConnexion);
                    }
                }).start();

                break;
        }
    }
}
