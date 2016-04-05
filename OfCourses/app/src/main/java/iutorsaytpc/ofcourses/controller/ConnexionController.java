package iutorsaytpc.ofcourses.controller;

import android.app.Activity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.bd.BD;
import iutorsaytpc.ofcourses.view.ConnexionView;
import iutorsaytpc.ofcourses.view.FragmentView;
import iutorsaytpc.ofcourses.view.EdtView;

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
                                    RelativeLayout content = ((RelativeLayout) ((Activity) view.getContext()).findViewById(R.id.layoutParent));
                                    content.removeAllViews();
                                    content.addView(new FragmentView(view.getContext()));
                                    //ajout de l'edt dans le frameLayout
                                    FrameLayout contentFrame = ((FrameLayout) ((Activity) view.getContext()).findViewById(R.id.frameLayoutFragment));
                                    contentFrame.addView(new EdtView(view.getContext()));
                                }
                            });
                        }
                    }
                }).start();
                break;
        }
    }
}
