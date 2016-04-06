package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Objects;

import iutorsaytpc.ofcourses.MainActivity;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.view.ConnexionView;
import iutorsaytpc.ofcourses.view.FragmentView;

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
                        int id = BD.isLogin(view.getLogin(), view.getPassword());

                        if(id >= 0) {
                            ((Activity) view.getContext()).runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    FragmentView tmpView = new FragmentView(view.getContext());
                                    ((MainActivity) view.getContext()).setViewShowed(tmpView);
                                    ((MainActivity) view.getContext()).setContentFragment();
                                }
                            });
                        }
                    }
                }).start();
                break;
        }
    }
}
