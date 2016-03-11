package iutorsaytpc.ofcourses.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.controller.ConnexionController;

/**
 * Created by VirusTwoIUT on 11/03/2016.
 */
public class ConnexionView extends LinearLayout {

    private Context context;
    private Button connexionButton;


    public ConnexionView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.content_connexion, this, true);
        connexionButton = (Button) findViewById(R.id.connexion);
    }
    private void bindViews(){
        ConnexionController controller = new ConnexionController(this);
        connexionButton.setOnClickListener(controller);
    }
}