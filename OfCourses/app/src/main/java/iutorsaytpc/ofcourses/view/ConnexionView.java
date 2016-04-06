package iutorsaytpc.ofcourses.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import iutorsaytpc.ofcourses.R;
import iutorsaytpc.ofcourses.controller.ConnexionController;
import iutorsaytpc.ofcourses.modele.SettingsSingleton;

/**
 * Created by VirusTwoIUT on 11/03/2016.
 */
public class ConnexionView extends LinearLayout {

    private Context context;
    private Button connexionButton;
    private TextView login;
    private TextView mdp;

    public ConnexionView(Context context) {
        super(context);
        this.context = context;

        inflate();
        bindViews();
    }

    private void inflate() {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layoutInflater.inflate(R.layout.content_connexion, this, true);
    }
    private void bindViews(){
        ConnexionController controller = new ConnexionController(this);

        // Bind des objets de la page connexion
        connexionButton = (Button) findViewById(R.id.connexion);
        login = (TextView) findViewById(R.id.email);
        mdp = (TextView) findViewById(R.id.password);

        connexionButton.setOnClickListener(controller);
    }
    public String getLogin(){
        return String.valueOf(login.getText());
    }
    public String getPassword(){
        return String.valueOf(mdp.getText());
    }
}