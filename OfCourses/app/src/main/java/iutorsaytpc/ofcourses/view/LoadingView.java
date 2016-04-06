package iutorsaytpc.ofcourses.view;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.modele.SettingsSingleton;

/**
 * Created by guillaumemartinez on 30/03/2016
 */
public class LoadingView extends RelativeLayout {

    private TextView loadingText;

    public LoadingView(Context context) {
        super(context);

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.loading_fragment, this, true);
    }

    private void bindViews() {
        loadingText = (TextView) findViewById(R.id.loadingText);
    }

    public void errorConnnexion() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingText.setText("Erreur de connexion");
            }
        });
    }

    public void errorLogin() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingText.setText("Login ou mdp incorrect");
            }
        });
    }

    public void reset() {
        ((Activity) getContext()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loadingText.setText("LOADING");
            }
        });
    }
}
